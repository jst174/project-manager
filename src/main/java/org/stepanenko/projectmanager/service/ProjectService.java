package org.stepanenko.projectmanager.service;

import org.springframework.stereotype.Service;
import org.stepanenko.projectmanager.model.Project;
import org.stepanenko.projectmanager.repository.ProjectRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;

import static java.lang.String.format;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public Project create(Project project) {
        return projectRepository.save(project);
    }

    public List<Project> getAll() {
        return projectRepository.findAll();
    }

    public Project getById(Long id) {
        return projectRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(format("Project by id = %s was not found", id)));
    }

    public Project update(Project project) {
        return projectRepository.save(project);
    }

    public void delete(Long id) {
        projectRepository.deleteById(id);
    }
}
