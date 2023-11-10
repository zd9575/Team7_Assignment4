package assign4.src.Repositories.Users;

import org.springframework.data.jpa.repository.JpaRepository;

import assign4.src.Models.Users.Member;

public interface MemberRepository extends JpaRepository<Member, String> {
    Member findByEmail(String email);
}