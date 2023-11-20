package web.taskAssigner.Controller;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import web.taskAssigner.Model.Tasks;
import web.taskAssigner.Service.TasksService;

@Controller
public class TasksController {


    @Autowired
    TasksService tasksService;

    @PostMapping("/createTasks")
    public String postTask(@Valid @ModelAttribute("tasks") Tasks tasks) {
        tasksService.createTasks(tasks);
        return "createTasks";
    }
}
