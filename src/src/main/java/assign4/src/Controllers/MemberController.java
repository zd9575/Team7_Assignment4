package assign4.src.Controllers;

import java.io.IOException;
import java.util.List;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import assign4.src.Models.Member;
import assign4.src.Repositories.MemberRepository;
import assign4.src.Services.MemberService;

@SessionAttributes("currentMember") // Add this annotation
@Controller
public class MemberController {
    @Autowired
    private MemberRepository memberRepository;

    @GetMapping("/handleLogin") // Updated endpoint
    public String handleLogin(@Valid @ModelAttribute("member") Member member,
            Model model) {
        if (member.getEmail() == null & member.getPassword() == null) {
            System.out.println("Please enter your login credentials");
        }
        System.out.println(member.toString());
        System.out.println(memberRepository.findByEmail(member.getEmail()));
        Member verifyMember = memberRepository.findByEmail(member.getEmail());
     

        if (verifyMember != null) { // Check verifyMember, not member
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            if (passwordEncoder.matches(member.getPassword(), verifyMember.getPassword())) {
                String memberRole = verifyMember.getRole();
                if (memberRole != null) {
                    model.addAttribute("currentMember", verifyMember);
                    return "redirect:/memberPage";
                }
            }
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




    @GetMapping("/memberPage")
    public String memberDashboard(Model model) { 
        Member currentMember = (Member) model.getAttribute("currentMember");
        if (currentMember != null) {
            model.addAttribute("memberName", currentMember.getFirstName() + " " + currentMember.getLastName());

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

    @Autowired
    MemberService memberService;

    @PostMapping("/registerHandling")
    public String postRegisterHandling(@Valid @ModelAttribute("member") Member member) {
        String selectedRole = member.getRole();
        memberService.createMember(member, selectedRole);
        return "login";
    }

    @GetMapping("/createTasks")
    public String showCreateTasks(){
        return "createTasks";
    }
}