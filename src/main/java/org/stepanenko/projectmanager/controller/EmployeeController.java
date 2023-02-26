package org.stepanenko.projectmanager.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.stepanenko.projectmanager.model.Employee;
import org.stepanenko.projectmanager.model.Project;
import org.stepanenko.projectmanager.service.EmployeeService;
import org.stepanenko.projectmanager.service.ProjectService;

import java.util.List;

@RestController
@RequestMapping("api/v1/employee")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final ProjectService projectService;

    public EmployeeController(EmployeeService employeeService, ProjectService projectService) {
        this.employeeService = employeeService;
        this.projectService = projectService;
    }

    @CrossOrigin
    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = employeeService.getAll();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable Long id) {
        Employee employee = employeeService.getById(id);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        Employee newEmployee = employeeService.save(employee);
        return new ResponseEntity<>(newEmployee, HttpStatus.CREATED);
    }

    @CrossOrigin
    @PutMapping
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee) {
        Employee updatedEmployee = employeeService.save(employee);
        return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
    }

    @CrossOrigin
    @DeleteMapping("{id}")
    public ResponseEntity<Employee> deleteEmployee(@PathVariable Long id) {
        employeeService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @CrossOrigin
    @GetMapping("{id}/projects")
    public ResponseEntity<List<Project>> getProjectsByEmployeeId(@PathVariable Long id){
        List<Project> projects = projectService.getProjectsByEmployeeId(id);
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }
}
