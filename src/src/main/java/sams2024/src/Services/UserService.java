package sams2024.src.Services;

import sams2024.src.DTO.MemberDto;
import sams2024.src.Models.Users.Member;

public interface UserService {
    void saveUser(MemberDto memberDto);

    Member findUserByEmail(String email);
}
