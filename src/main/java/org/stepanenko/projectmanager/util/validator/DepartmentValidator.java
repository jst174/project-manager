package org.stepanenko.projectmanager.util.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.stepanenko.projectmanager.model.Department;
import org.stepanenko.projectmanager.repository.DepartmentRepository;

import java.util.Objects;

@Component
public class DepartmentValidator implements Validator {

    private final DepartmentRepository departmentRepository;

    public DepartmentValidator(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Department.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Department department = (Department) target;

        if (departmentRepository.findByName(department.getName())
                .filter(e -> !Objects.equals(e.getId(), department.getId()))
                .isPresent()) {
            errors.rejectValue("name", "", "Department with this name already exist");
        }
    }
}
