package org.stepanenko.projectmanager.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.stepanenko.projectmanager.model.Department;
import org.stepanenko.projectmanager.service.DepartmentService;

import java.util.List;

@RestController
@RequestMapping("api/v1/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @CrossOrigin
    @GetMapping
    public ResponseEntity<List<Department>> getAllDepartments() {
        List<Department> departments = departmentService.getAll();
        return new ResponseEntity<>(departments, HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("{id}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable Long id) {
        Department department = departmentService.getById(id);
        return new ResponseEntity<>(department, HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping
    public ResponseEntity<Department> createDepartment(@RequestBody Department department) {
        Department newDepartment = departmentService.save(department);
        return new ResponseEntity<>(newDepartment, HttpStatus.CREATED);
    }

    @CrossOrigin
    @PutMapping
    public ResponseEntity<Department> updateDepartment(@RequestBody Department department){
        Department updatedDepartment = departmentService.save(department);
        return new ResponseEntity<>(updatedDepartment, HttpStatus.OK);
    }

    @CrossOrigin
    @DeleteMapping("{id}")
    public ResponseEntity<Department> deleteDepartment(@PathVariable Long id){
        departmentService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
