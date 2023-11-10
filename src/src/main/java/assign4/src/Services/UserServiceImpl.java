package assign4.src.Services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import assign4.src.DTO.MemberDto;
import assign4.src.Models.Users.Member;
import assign4.src.Repositories.Users.MemberRepository;

@Service
public class UserServiceImpl implements UserService {

    private MemberRepository memberRepository;
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(MemberRepository memberRepository,
            PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveUser(MemberDto memberDto) {
        Member member = new Member();
        member.setFirstName(memberDto.getFirstName());
        member.setLastName(memberDto.getLastName());
        member.setEmail(memberDto.getEmail());
        // encrypt the password using spring security
        member.setPassword(passwordEncoder.encode(memberDto.getPassword()));

        memberRepository.save(member);
    }

    @Override
    public Member findUserByEmail(String email) {
        return memberRepository.findByEmail(email);
    }
}