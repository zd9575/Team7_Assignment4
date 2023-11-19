package assign4.src.Services;


import assign4.src.Models.Tasks;
import assign4.src.Repositories.TasksRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

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
