package org.stepanenko.projectmanager.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.stepanenko.projectmanager.model.Employee;
import org.stepanenko.projectmanager.model.Project;
import org.stepanenko.projectmanager.service.EmployeeService;
import org.stepanenko.projectmanager.service.ProjectService;
import org.stepanenko.projectmanager.util.CheckValidationErrors;
import org.stepanenko.projectmanager.util.validator.EmployeeValidator;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/employee")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final ProjectService projectService;
    private final EmployeeValidator employeeValidator;

    public EmployeeController(EmployeeService employeeService, ProjectService projectService, EmployeeValidator employeeValidator) {
        this.employeeService = employeeService;
        this.projectService = projectService;
        this.employeeValidator = employeeValidator;
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
    public ResponseEntity<Employee> createEmployee(@RequestBody @Valid Employee employee,
                                                   BindingResult bindingResult) {
        employeeValidator.validate(employee, bindingResult);
        CheckValidationErrors.check(bindingResult);
        Employee newEmployee = employeeService.save(employee);
        return new ResponseEntity<>(newEmployee, HttpStatus.CREATED);
    }

    @CrossOrigin
    @PutMapping
    public ResponseEntity<Employee> updateEmployee(@RequestBody @Valid Employee employee,
                                                   BindingResult bindingResult) {
        employeeValidator.validate(employee, bindingResult);
        CheckValidationErrors.check(bindingResult);
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
    public ResponseEntity<List<Project>> getProjectsByEmployeeId(@PathVariable Long id) {
        List<Project> projects = projectService.getProjectsByEmployeeId(id);
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }
}
