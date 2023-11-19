package assign4.src.Services;

import java.util.List;

import assign4.src.Models.Member;
import assign4.src.Models.Task;

public interface TaskService {
    List<Task> getAllTasks(Member assignedMember);
    Task getTaskById(Long id);
    void saveTask(Task task);
    void deleteTask(Long id);
    boolean existsTaskWithId(Long taskId);
}