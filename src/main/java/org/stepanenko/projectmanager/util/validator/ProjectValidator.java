package org.stepanenko.projectmanager.util.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.stepanenko.projectmanager.model.Project;
import org.stepanenko.projectmanager.repository.ProjectRepository;

import java.util.Objects;

@Component
public class ProjectValidator implements Validator {

    private final ProjectRepository projectRepository;

    public ProjectValidator(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Project.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Project project = (Project) target;

        if (projectRepository.findByName(project.getName())
                .filter(e -> !Objects.equals(e.getId(), project.getId()))
                .isPresent()) {
            errors.rejectValue("name", "", "Project with this name already exist");
        }
    }
}
