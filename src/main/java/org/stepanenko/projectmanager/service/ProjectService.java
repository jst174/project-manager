package org.stepanenko.projectmanager.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.stepanenko.projectmanager.model.Project;
import org.stepanenko.projectmanager.repository.ProjectRepository;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

import static java.lang.String.format;

@Slf4j
@Service
@Transactional
public class ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public Project save(Project project) {
        log.info("Save project {}", project.getName());
        return projectRepository.save(project);
    }

    public List<Project> getAll() {
        log.info("Get all projects");
        return projectRepository.findAll();
    }

    public Project getById(Long id) {
        log.info("Get project by id = {}", id);
        return projectRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException(format("Project with id = %s was not found", id)));
    }

    public void delete(Long id) {
        log.info("Delete project by id = {}", id);
        if (!projectRepository.existsById(id)) {
            throw new EntityNotFoundException(format("Project with id = %s was not found", id));
        }
        projectRepository.deleteById(id);
    }
}
