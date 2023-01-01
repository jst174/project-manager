package org.stepanenko.projectmanager.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;
    private String name;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;
    private Client client;
    private Employee projectManager;
    private Employee projectManagerAssistant;
    private List<Employee> employees;
}
