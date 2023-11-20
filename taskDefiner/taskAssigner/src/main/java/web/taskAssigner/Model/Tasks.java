package web.taskAssigner.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

@Entity
public class Tasks {

    public Tasks(String emailValue, String taskValue) {
        this.emailValue = emailValue;
        this.taskValue = taskValue;
    }

    public Tasks(){ }

    @Id
    @Email
    @NotEmpty
    @Column(name = "email")
    private String emailValue;

    @NotEmpty
    @Column(name = "task")
    private String taskValue;

    public String getEmailValue() {
        return emailValue;
    }

    public void setEmailValue(String emailValue) {
        this.emailValue = emailValue;
    }

    public String getTaskValue() {
        return taskValue;
    }

    public void setTaskValue(String taskValue) {
        this.taskValue = taskValue;
    }

    @Override
    public String toString() {
        return "[email: " + emailValue + " task: " + taskValue + "]";
    }

}
