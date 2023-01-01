package org.stepanenko.projectmanager.service;

import org.springframework.stereotype.Service;
import org.stepanenko.projectmanager.model.Employee;
import org.stepanenko.projectmanager.repository.EmployeeRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;

import static java.lang.String.format;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee create(Employee employee){
        return employeeRepository.save(employee);
    }

    public List<Employee> getAll(){
        return employeeRepository.findAll();
    }

    public Employee update(Employee employee){
        return employeeRepository.save(employee);
    }

    public Employee getById(Long id) {
        return employeeRepository.findById(id).orElseThrow(()->new EntityNotFoundException(format("User by id = %s was not found", id)));
    }

    public void delete(Long id){
        employeeRepository.deleteById(id);
    }

}
