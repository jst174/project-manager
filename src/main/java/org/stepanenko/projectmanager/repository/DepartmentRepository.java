package org.stepanenko.projectmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.stepanenko.projectmanager.model.Client;
import org.stepanenko.projectmanager.model.Department;

import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    @Query("SELECT d FROM Department d WHERE d.name = :name")
    Optional<Department> findByName(@Param("name") String name);
}
