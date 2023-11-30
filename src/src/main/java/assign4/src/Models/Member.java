package assign4.src.Models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import jakarta.validation.constraints.*;
import jakarta.persistence.*;

@Entity
public class Member {


    // LACK OF FORM INPUT VALIDATION FOR REGISTRATION (ARCH BREAKER 4)
    /* If each entity annotation such as email, is not designated to be "NotEmpty", the form
    *  will accept null values to be inserted into the database. This is problematic because
    *  it will break the requirements needed for the member's credentials.
    */
    @Id
    @Email
    // @NotEmpty
    @Column(name = "email")
    private String email;

    // @NotEmpty
    @Column(name = "password")
    private String password;

    // @NotEmpty
    @Column(name = "first_name")
    private String firstName;

    // @NotEmpty
    @Column(name = "last_name")
    private String lastName;

    // @NotEmpty
    @Column(name = "role")
    private String role;

    @OneToMany(mappedBy = "assignedMember")
    private List<Task> tasks;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Member(String email, String firstName, String lastName, String password, String role) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.role = role;
    }

    public Member() {
        // Default no-argument constructor.
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "[email: " + email + " password= " + password + "role: " + role + "]";
    }

    public Collection<String> getRoles() {
        Collection<String> rolesCollection = new ArrayList<>();
        if (role instanceof String) {
            rolesCollection.add(role);
        }
        return rolesCollection;
    }
}