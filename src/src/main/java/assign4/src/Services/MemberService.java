package assign4.src.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import assign4.src.Models.Member;
import assign4.src.Repositories.MemberRepository;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    public Member createMember(Member member, String selectedRole) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        member.setPassword(encoder.encode(member.getPassword()));

        Member existingMember = memberRepository.findByEmail(member.getEmail());

        if (existingMember == null) {
            Member newMember = new Member(member.getEmail(), member.getFirstName(), member.getLastName(),
                    member.getPassword(), selectedRole);
            memberRepository.save(newMember);
            
            return newMember;
        } else {
            System.out.println("Member already exists");
            return existingMember;
        }
    }

    public Member verifyMember(Member member) {
        // Additional verification logic if needed
        return member;
    }
}