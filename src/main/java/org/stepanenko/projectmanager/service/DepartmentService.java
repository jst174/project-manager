package org.stepanenko.projectmanager.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.stepanenko.projectmanager.exceptions.BadRequestException;
import org.stepanenko.projectmanager.model.Department;
import org.stepanenko.projectmanager.repository.DepartmentRepository;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

import static java.lang.String.format;

@Slf4j
@Service
@Transactional
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final EmployeeService employeeService;

    public DepartmentService(DepartmentRepository departmentRepository, EmployeeService employeeService) {
        this.departmentRepository = departmentRepository;
        this.employeeService = employeeService;
    }

    public Department save(Department department) {
        log.info("Save department {}", department.getName());
        return departmentRepository.save(department);
    }

    public List<Department> getAll() {
        log.info("Get all departments");
        return departmentRepository.findAll();
    }

    public Department getById(Long id) {
        log.info("Get department by id = {}", id);
        return departmentRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException(format("Department with id = %s was not found", id)));
    }

    public void delete(Long id) {
        log.info("Delete department by id = {}", id);
        if (!departmentRepository.existsById(id)) {
            throw new EntityNotFoundException(format("Department with id = %s was not found", id));
        }
        if (!employeeService.getEmployeesByDepartmentId(id).isEmpty()){
            throw new BadRequestException(format("Department with id = %s cannot be deleted because it has employees", id));
        }
        departmentRepository.deleteById(id);
    }
}
