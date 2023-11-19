package assign4.src.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import assign4.src.Models.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, String> {
    Member findByEmail(String email);

    List<Member> findAllByRole(String string);
}