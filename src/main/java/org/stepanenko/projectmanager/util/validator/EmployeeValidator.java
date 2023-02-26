package org.stepanenko.projectmanager.util.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.stepanenko.projectmanager.model.Employee;
import org.stepanenko.projectmanager.repository.EmployeeRepository;

import java.util.Objects;
import java.util.Optional;

@Component
public class EmployeeValidator implements Validator {

    private final EmployeeRepository employeeRepository;

    public EmployeeValidator(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Employee.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Employee employee = (Employee) target;
        if (employeeRepository.findByEmail(employee.getEmail())
                .filter(e -> !Objects.equals(e.getId(), employee.getId()))
                .isPresent()) {
            errors.rejectValue("email", "", "This email is already taken");
        }

        if (employeeRepository.findByPhone(employee.getPhone())
                .filter(e -> !Objects.equals(e.getId(), employee.getId()))
                .isPresent()) {
            errors.rejectValue("phone", "", "This phone is already taken");
        }
    }


}
