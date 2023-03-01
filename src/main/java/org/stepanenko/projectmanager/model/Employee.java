package org.stepanenko.projectmanager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;
    @NotEmpty(message = "First name must not be empty")
    @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters long")
    private String firstName;
    @NotEmpty(message = "Last name must not be empty")
    @Size(min = 2, max = 100, message = "Last name must be between 2 and 100 characters long")
    private String lastName;
    @NotEmpty(message = "Email must not be empty")
    @Email(message = "Email must be valid")
    private String email;
    @NotEmpty(message = "Job title must not be empty")
    private String jobTitle;
    @NotEmpty(message = "Phone number must not be empty")
    private String phone;
    private String imageUrl;
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
    @NotEmpty(message = "Password must not be empty")
    private String password;
    @ManyToMany(mappedBy = "employees")
    @JsonIgnore
    private List<Project> projects = new ArrayList<>();

    public Employee() {

    }

    public Employee(String firstName, String lastName, String email,
                    String jobTitle, String phone, String imageUrl, Department department) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.jobTitle = jobTitle;
        this.phone = phone;
        this.imageUrl = imageUrl;
        this.department = department;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Department getDepartment() {
        return department;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }
}
