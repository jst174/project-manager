package org.stepanenko.projectmanager.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.stepanenko.projectmanager.model.Project;
import org.stepanenko.projectmanager.service.ProjectService;
import org.stepanenko.projectmanager.util.CheckValidationErrors;
import org.stepanenko.projectmanager.util.validator.ProjectValidator;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/projects")
public class ProjectController {

    private final ProjectService projectService;
    private final ProjectValidator projectValidator;

    public ProjectController(ProjectService projectService, ProjectValidator projectValidator) {
        this.projectService = projectService;
        this.projectValidator = projectValidator;
    }

    @CrossOrigin
    @GetMapping
    public ResponseEntity<List<Project>> getAllProjects() {
        List<Project> projects = projectService.getAll();
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable Long id) {
        Project project = projectService.getById(id);
        return new ResponseEntity<>(project, HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping
    public ResponseEntity<Project> createProject(@RequestBody @Valid Project project,
                                                 BindingResult bindingResult) {
        projectValidator.validate(project, bindingResult);
        CheckValidationErrors.check(bindingResult);
        Project newProject = projectService.save(project);
        return new ResponseEntity<>(newProject, HttpStatus.CREATED);
    }

    @CrossOrigin
    @PutMapping
    public ResponseEntity<Project> updateProject(@RequestBody @Valid Project project,
                                                 BindingResult bindingResult ) {
        projectValidator.validate(project, bindingResult);
        CheckValidationErrors.check(bindingResult);
        Project updatedProject = projectService.save(project);
        return new ResponseEntity<>(updatedProject, HttpStatus.OK);
    }

    @CrossOrigin
    @DeleteMapping("{id}")
    public ResponseEntity<Project> deleteProject(@PathVariable Long id) {
        projectService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
