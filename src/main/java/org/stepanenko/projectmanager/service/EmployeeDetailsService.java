package org.stepanenko.projectmanager.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.stepanenko.projectmanager.model.Employee;
import org.stepanenko.projectmanager.repository.EmployeeRepository;
import org.stepanenko.projectmanager.security.EmployeeDetails;

import java.util.Optional;

@Service
public class EmployeeDetailsService implements UserDetailsService {

    private final EmployeeRepository employeeRepository;

    public EmployeeDetailsService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Employee> employee = employeeRepository.findByEmail(username);
        if (employee.isEmpty()){
            throw new UsernameNotFoundException("User not found!");
        }
        return new EmployeeDetails(employee.get());
    }
}
