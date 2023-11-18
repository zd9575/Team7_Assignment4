package assign4.src.Models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

@Entity
public class FormData {

    @NotEmpty
    @Column(name = "task")
    private String textAreaValue;

    @Id
    @Email
    @NotEmpty
    @Column(name = "email")
    private String emailValue;

    public FormData(String textAreaValue, String emailValue) {
        this.textAreaValue = textAreaValue;
        this.emailValue = emailValue;
    }

    public FormData(){ };


    public String getTextAreaValue() {
        return textAreaValue;
    }

    public void setTextAreaValue(String textAreaValue) {
        this.textAreaValue = textAreaValue;
    }

    public String getEmailValue() {
        return emailValue;
    }

    public void setEmailValue(String emailValue) {
        this.emailValue = emailValue;
    }

    @Override
    public String toString() {
        return getEmailValue() + " " + getTextAreaValue();
    }
}
