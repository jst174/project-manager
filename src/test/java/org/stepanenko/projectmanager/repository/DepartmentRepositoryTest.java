package org.stepanenko.projectmanager.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.stepanenko.projectmanager.model.Department;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class DepartmentRepositoryTest {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Test
    public void givenExistingDepartmentName_whenFindByName_thenReturnDepartment() {
        String name = "AR";
        Department expected = new Department(name);

        departmentRepository.save(expected);

        Department actual = departmentRepository.findByName(name).get();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void givenNotExistingDepartmentName_whenFindByName_thenReturnOptionalEmpty() {
        String name = "AR";

        Optional<Department> actual = departmentRepository.findByName(name);

        assertThat(actual).isEqualTo(Optional.empty());
    }
}