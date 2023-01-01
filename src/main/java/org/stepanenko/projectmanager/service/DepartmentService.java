package org.stepanenko.projectmanager.service;

import org.springframework.stereotype.Service;
import org.stepanenko.projectmanager.model.Department;
import org.stepanenko.projectmanager.repository.DepartmentRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;

import static java.lang.String.format;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public Department create(Department department) {
        return departmentRepository.save(department);
    }

    public List<Department> getAll() {
        return departmentRepository.findAll();
    }

    public Department getById(Long id) {
        return departmentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(format("Department by id = %s was not found")));
    }

    public Department update(Department department) {
        return departmentRepository.save(department);
    }

    public void delete(Long id) {
        departmentRepository.deleteById(id);
    }
}
