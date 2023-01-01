package org.stepanenko.projectmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.stepanenko.projectmanager.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
