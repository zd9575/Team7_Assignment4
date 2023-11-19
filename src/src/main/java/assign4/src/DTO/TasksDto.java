package assign4.src.DTO;



import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TasksDto {

    @NotEmpty(message = "Email should not be empty")
    @Email
    private String emailValue;

    @NotEmpty
    private String taskValue;

}
