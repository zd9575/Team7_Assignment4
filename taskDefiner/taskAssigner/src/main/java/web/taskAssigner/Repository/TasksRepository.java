package web.taskAssigner.Repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import web.taskAssigner.Model.Tasks;

@Repository
public interface TasksRepository extends JpaRepository<Tasks, String>{

}
