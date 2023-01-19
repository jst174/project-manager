package org.stepanenko.projectmanager.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.stepanenko.projectmanager.model.Employee;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @AfterEach
    void tearDown() {
        employeeRepository.deleteAll();
    }

    @Test
    void givenNotExistsEmail_whenIsExistsEmail_returnEmpty() {
        String email = "martin84@gmail.com";

        Optional<Employee> actual = employeeRepository.findByEmail(email);

        assertThat(actual).isEqualTo(Optional.empty());
    }

    @Test
    void givenExistsEmail_whenIsExistsEmail_returnEmployee() {
        String email = "martin@gmail.com";
        Employee expected = new Employee(
                "Robert",
                "Martin",
                email,
                "Architect",
                "234234324",
                "some url");

        employeeRepository.save(expected);

        Employee actual = employeeRepository.findByEmail(email).get();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void givenNotExistsPhone_whenIsExistsPhone_returnFalse() {
        String phone = "099324423643";

        Optional<Employee> actual = employeeRepository.findByPhone(phone);

        assertThat(actual).isEqualTo(Optional.empty());
    }

    @Test
    void givenExistsPhone_whenIsExistsPhone_returnTrue() {
        String phone = "099324423643";
        Employee expected = new Employee(
                "Robert",
                "Martin",
                "martin@gmail.com",
                "Architect",
                phone,
                "some url");

        employeeRepository.save(expected);

        Employee actual = employeeRepository.findByPhone(phone).get();

        assertThat(actual).isEqualTo(expected);
    }
}