package assign4.src.Controllers;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import assign4.src.Models.*;
import assign4.src.Repositories.MemberRepository;
import assign4.src.Services.*;

@SessionAttributes({ "currentMember", "task" })
@Controller
public class MemberController {
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    MemberService memberService;
    @Autowired
    TaskService taskService;


    @GetMapping("/handleLogin")
    public String handleLogin(@Valid @ModelAttribute("member") Member member,
                              Model model) {
        if (member.getEmail() == null & member.getPassword() == null) {
            System.out.println("Please enter your login credentials");
        }
        Member verifyMember = memberRepository.findByEmail(member.getEmail());

        if (verifyMember != null) { // Check verifyMember, not member
            // PASSWORD ENCRYPTION FAILURE (ARCH BREAKER 3)
            /* If the login password is not encoded, and the database password is, it will fail to login
             *  passwordMatchFail will check if the passwords are the same, whereas passwordEncoder encodes
             *  the raw password to compare with the encoded password
             */
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            boolean passwordsMatch = passwordEncoder.matches(member.getPassword(), verifyMember.getPassword());

            boolean passwordsMatchFail = member.getPassword().equals(verifyMember.getPassword());
            if (passwordsMatch) {
                String memberRole = verifyMember.getRole();
                if (memberRole != null) {
                    // AUTHENTICATION FAILURE (ARCH BREAKER 1)
                    /*
                     * Users will try to log in but if the user is not added to the model object
                     * of the spring boot controller, they will fail to login and be redirected
                     * back to the login page.
                     */
                    model.addAttribute("currentMember", verifyMember);
                    return "redirect:/memberPage";
                }
            }else{
                model.addAttribute("passwordError", "Username or password is incorrect. Please try again!");
            }
        }else{
            model.addAttribute("loginError", "User not found, please register for a new account");
        }

        model.addAttribute("waitForLogin", true);
        return "login";
    }

    @GetMapping("/login")
    public String getLogin() {
        return "login";
    }

    @PostMapping("/login")
    public String postLogin(Model model, Member member,
                            @RequestParam(name = "loginButton", required = false) String loginButton,
                            @RequestParam(name = "registerButton", required = false) String registerButton) throws IOException {
        if (member != null) {
            if (loginButton != null) {
                model.addAttribute("member",
                        new Member(member.getEmail(), member.getFirstName(), member.getLastName(),
                                member.getPassword(), member.getRole()));
                return handleLogin(member, model);
            } else if (registerButton != null) {
                return getRegister(model, member);
            }
        }
        return "login";
    }

    private Long generateRandomTaskId() {
        Random random = new Random();
        long lowerBound = 0;
        long upperBound = 1000;
        return lowerBound + random.nextInt((int) (upperBound - lowerBound + 1));
    }

    @GetMapping("/memberPage")
    public String memberDashboard(Model model) {
        Member currentMember = (Member) model.getAttribute("currentMember");
        if (currentMember != null) {
            model.addAttribute("memberName", currentMember.getFirstName() + " " + currentMember.getLastName());
            model.addAttribute("members", memberService.getAllEmployees());
            model.addAttribute("task", new Task());
            System.out.println(currentMember.toString());
            List<Task> allTasks = taskService.getAllTasks(currentMember);
            model.addAttribute("allTasks", allTasks);
            return "memberDash";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("/register")
    public String getRegister(Model model, Member member) {
        model.addAttribute("member", new Member(member.getEmail(), member.getFirstName(), member.getLastName(),
                member.getPassword(), member.getRole()));
        return "registration";
    }

    @PostMapping("/registerHandling")
    public String postRegisterHandling(@Valid @ModelAttribute("member") Member member) {
        String selectedRole = member.getRole();
        memberService.createMember(member, selectedRole);
        return "login";
    }

    @PostMapping("/taskHandling")
    public String postTaskHandling(@Valid @ModelAttribute("task") Task task, Model model) {
        Long randomTaskId;
        Member currentMember = (Member) model.getAttribute("currentMember");

        // AUTHORIZATION FAILURE (ARCH BREAKER 2)
        /* If the task form fails to check the role of the current member logged in, it
         * will result in an authorization failure.
         */
//        if(currentMember == null){
//            return "login";
//        }
        if(currentMember.getRole() != "Manager"){
            List<Task> allTasks = taskService.getAllTasks(currentMember);
            model.addAttribute("allTasks", allTasks);
            return "login";
        }

        Member assignedMember = task.getAssignedMember();
        model.addAttribute("members", memberService.getAllEmployees());
        do {
            randomTaskId = generateRandomTaskId();
        } while (taskService.existsTaskWithId(randomTaskId));

        task.setId(randomTaskId);
        task.setAssignedMember(assignedMember);
        taskService.saveTask(task);

        List<Task> allTasks = taskService.getAllTasks(currentMember);
        model.addAttribute("allTasks", allTasks);
        return "memberDash";
    }

    @GetMapping("/logout")
    public String getLogout() {
        return "logout";
    }
}