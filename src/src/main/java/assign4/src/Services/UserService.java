package assign4.src.Services;

import assign4.src.DTO.MemberDto;
import assign4.src.Models.Users.Member;

public interface UserService {
    void saveUser(MemberDto memberDto);

    Member findUserByEmail(String email);
}
