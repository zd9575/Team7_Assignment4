package sams2024.src.Controllers;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import sams2024.src.Models.Users.Member;
import sams2024.src.Repositories.Users.MemberRepository;
import sams2024.src.Services.MemberService;

@Controller
public class MemberController {
    @Autowired
    private MemberRepository memberRepository;

    @PostMapping("/login")
    public String loginHandler(@Valid @ModelAttribute("member") Member member,
            @RequestParam(name = "loginButton", required = false) String loginButton,
            Model model) {
        Member verifyMember = memberRepository.findByEmail(member.getEmail());

        if (verifyMember != null) { // Check verifyMember, not member
            // Compare the input password with the stored hashed password and authenticate
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            if (passwordEncoder.matches(member.getPassword(), verifyMember.getPassword())) {
                String memberRole = verifyMember.getRole();
                if (memberRole != null) {
                    if ("PCM".equals(memberRole)) {
                        return "pcm";
                    } else if ("PCC".equals(memberRole)) {
                        return "pcc";
                    } else if ("Author".equals(memberRole)) {
                        return "author";
                    }
                }
            }
        }

        model.addAttribute("waitForLogin", true);
        return "login";
    }

    @Autowired
    MemberService memberService;

    @GetMapping("/login")
    public String showLoginForm(Model model, @Valid @ModelAttribute("member") Member member) {
        model.addAttribute("member", new Member(member.getUsername(), member.getFirstName(), member.getLastName(),
                member.getPassword(), member.getRole()));
        return "login";
    }

    @GetMapping("/")
    public String homepage() {
        return "login";
    }

    @GetMapping("/pcc")
    public String pccDashboard(Model model) {
        return "pcc";
    }

    @GetMapping("/pcm")
    public String pcmDashboard(Model model) {
        return "pcm";
    }

    @GetMapping("/author")
    public String authorDashboard(Model model) {
        return "author";
    }

    @GetMapping("/register")
    public String registerForm(Model model, @Valid Member member) {
        model.addAttribute("member", new Member(member.getEmail(), member.getFirstName(), member.getLastName(),
                member.getPassword(), member.getRole()));
        return "registration";
    }

    @PostMapping("/register")
    public String registerMember(@Valid Member member, @RequestParam String selectedRole) {
        memberService.createMember(member, selectedRole);
        return "login";
    }
}