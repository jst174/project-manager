package org.stepanenko.projectmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.stepanenko.projectmanager.model.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
