package org.stepanenko.projectmanager.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.stepanenko.projectmanager.exceptions.BadRequestException;
import org.stepanenko.projectmanager.model.Employee;
import org.stepanenko.projectmanager.repository.EmployeeRepository;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

import static java.lang.String.format;

@Slf4j
@Service
@Transactional
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    public Employee save(Employee employee) {
        log.info("Save employee {} {}", employee.getFirstName(), employee.getLastName());
        verifyEmailUnique(employee);
        verifyPhoneUnique(employee);
        return employeeRepository.save(employee);
    }

    public List<Employee> getAll() {
        log.info("Get all employees");
        return employeeRepository.findAll();
    }

    public Employee getById(Long id) {
        log.info("Get employee by id = {}", id);
        return employeeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(format("User by id = %s was not found", id)));
    }

    public void delete(Long id) {
        log.info("Delete employee by id = {}", id);
        if (!employeeRepository.existsById(id)) {
            throw new EntityNotFoundException(format("User by id = %s was not found", id));
        }
        employeeRepository.deleteById(id);
    }

    public List<Employee> getEmployeesByDepartmentId(Long id) {
        log.info("Get employee by department's id = {}", id);
        return employeeRepository.getEmployeesByDepartmentId(id);
    }

    private void verifyEmailUnique(Employee employee) {
        if (employeeRepository.findByEmail(employee.getEmail())
                .filter(e -> !Objects.equals(e.getId(), employee.getId()))
                .isPresent()) {
            throw new BadRequestException(
                    format("Employee with email %s already exist", employee.getEmail()));
        }
    }

    private void verifyPhoneUnique(Employee employee) {
        if (employeeRepository.findByPhone(employee.getPhone())
                .filter(e -> !Objects.equals(e.getId(), employee.getId()))
                .isPresent()) {
            throw new BadRequestException(
                    format("Employee with phone %s already exist", employee.getPhone()));
        }
    }
}
