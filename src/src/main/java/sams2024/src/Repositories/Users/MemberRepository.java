package sams2024.src.Repositories.Users;

import org.springframework.data.jpa.repository.JpaRepository;

import sams2024.src.Models.Users.Member;

public interface MemberRepository extends JpaRepository<Member, String> {
    Member findByEmail(String email);
}