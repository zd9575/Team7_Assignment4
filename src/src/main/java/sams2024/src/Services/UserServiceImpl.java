package sams2024.src.Services;

import sams2024.src.DTO.MemberDto;
import sams2024.src.Models.Users.Member;

import sams2024.src.Repositories.Users.MemberRepository;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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