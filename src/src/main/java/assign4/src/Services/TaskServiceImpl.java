package assign4.src.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import assign4.src.Models.Member;
import assign4.src.Models.Task;
import assign4.src.Repositories.TaskRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public List<Task> getAllTasks(Member member) {
        if(member.getRole().equals("Manager")){
            return taskRepository.findAll();
        }else{
            return taskRepository.findByAssignedMember(member);
        }
    }

    @Override
    public Task getTaskById(Long id) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        return optionalTask.orElse(null);
    }

    @Override
    public void saveTask(Task task) {
        taskRepository.save(task);
    }

    @Override
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    @Override
    public boolean existsTaskWithId(Long taskId) {
        return taskRepository.existsById(taskId);
    }
}