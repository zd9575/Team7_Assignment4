package assign4.src.Repositories;


import assign4.src.Models.Member;
import assign4.src.Models.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TasksRepository extends JpaRepository<Tasks, String>{

}
