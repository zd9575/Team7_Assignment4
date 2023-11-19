package assign4.src.Controllers;

import assign4.src.Models.Member;
import assign4.src.Models.Tasks;
import assign4.src.Services.MemberService;
import assign4.src.Services.TasksService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TasksController {


    @Autowired
    TasksService tasksService;

    @PostMapping("/createTasks")
    public String postTask(@Valid @ModelAttribute("tasks") Tasks tasks) {
        tasksService.createTasks(tasks);
        return "memberDash";
    }
}
