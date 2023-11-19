package assign4.src.Models;

import jakarta.persistence.*;

@Entity
public class Task {

    @Id

    private Long id;
    private String name;
    private String description;
    @ManyToOne
    private Member assignedMember;

    private String member;

    public String getMember() {
        return member;
    }

    public void setMember(String member) {
        this.member = member;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Member getAssignedMember() {
        return assignedMember;
    }

    public void setAssignedMember(Member assignedMember) {
        this.assignedMember = assignedMember;
    }

     public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
   
    public Task() {
        // Default no-argument constructor.
    }

    public Task(String name, Long id, String description, Member assignedMember) {
        this.name = name;
        this.id = id;
        this.description = description;
        this.assignedMember = assignedMember;
    }

    @Override
    public String toString() {
        return "[id: " + id + " name= " + name + "description: " + description +  "assignedMember : " + assignedMember + "]";
    }
}