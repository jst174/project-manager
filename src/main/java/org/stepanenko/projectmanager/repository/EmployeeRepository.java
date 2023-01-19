package org.stepanenko.projectmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.stepanenko.projectmanager.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query(
            "SELECT CASE WHEN COUNT(e) > 0 THEN " +
                    "TRUE ELSE FALSE END " +
                    "FROM Employee e " +
                    "WHERE e.email = ?1"
    )
    Boolean isExistsEmail(String email);

    @Query(
            "SELECT CASE WHEN COUNT(e) > 0 THEN " +
                    "TRUE ELSE FALSE END " +
                    "FROM Employee e " +
                    "WHERE e.phone = ?1"
    )
    Boolean isExistsPhone(String phone);
}
