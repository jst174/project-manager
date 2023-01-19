package org.stepanenko.projectmanager.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.stepanenko.projectmanager.model.Employee;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @AfterEach
    void tearDown() {
        employeeRepository.deleteAll();
    }

    @Test
    void givenNotExistsEmail_whenIsExistsEmail_returnFalse() {
        String email = "martin84@gmail.com";

        boolean expected = employeeRepository.isExistsEmail(email);

        assertThat(expected).isFalse();
    }

    @Test
    void givenExistsEmail_whenIsExistsEmail_returnTrue() {
        String email = "martin@gmail.com";
        Employee employee = new Employee(
                "Robert",
                "Martin",
                email,
                "Architect",
                "234234324",
                "some url");

        employeeRepository.save(employee);

        boolean expected = employeeRepository.isExistsEmail(email);

        assertThat(expected).isTrue();
    }

    @Test
    void givenNotExistsPhone_whenIsExistsPhone_returnFalse() {
        String phone = "099324423643";

        boolean expected = employeeRepository.isExistsPhone(phone);

        assertThat(expected).isFalse();
    }

    @Test
    void givenExistsPhone_whenIsExistsPhone_returnTrue() {
        String phone = "099324423643";
        Employee employee = new Employee(
                "Robert",
                "Martin",
                "martin@gmail.com",
                "Architect",
                phone,
                "some url");

        employeeRepository.save(employee);

        boolean expected = employeeRepository.isExistsPhone(phone);

        assertThat(expected).isTrue();
    }
}