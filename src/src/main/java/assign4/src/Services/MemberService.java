package assign4.src.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import assign4.src.Models.Users.Member;
import assign4.src.Repositories.Users.MemberRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    public Member createMember(Member member, String selectedRole) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        member.setPassword(encoder.encode(member.getPassword()));

        Member memberRole = memberRepository.findByEmail(member.getEmail());

        if (memberRole == null) {
            // Save the selected role
            memberRole = new Member(member.getEmail(), member.getFirstName(), member.getLastName(),
                    member.getPassword(), selectedRole);
            memberRepository.save(memberRole);
        }

        List<Member> membersList = new ArrayList<>();
        membersList.add(memberRole);
        member.setRole(selectedRole);
        memberRepository.save(member);
        return member;
    }

    public Member verifyMember(Member member) {
        return member;
    }

}