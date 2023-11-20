package web.taskAssigner.Service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.taskAssigner.Model.Tasks;
import web.taskAssigner.Repository.TasksRepository;

@Service
public class TasksService {

    @Autowired
    private TasksRepository tasksRepository;

    public Tasks createTasks(Tasks tasks) {


            Tasks newTask = new Tasks(tasks.getEmailValue(), tasks.getTaskValue());
            tasksRepository.save(newTask);

            return newTask;

    }

}
