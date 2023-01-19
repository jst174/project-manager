package org.stepanenko.projectmanager.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.stepanenko.projectmanager.exceptions.BadRequestException;
import org.stepanenko.projectmanager.model.Employee;
import org.stepanenko.projectmanager.repository.EmployeeRepository;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

import static java.lang.String.format;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;
    private EmployeeService employeeServiceTest;

    @BeforeEach
    void setUp() {
        employeeServiceTest = new EmployeeService(employeeRepository);
    }

    @Test
    void givenEmployee_whenSave_thenSaved() {
        Employee employee = new Employee(
                "Robert",
                "Martin",
                "martin@gmail.com",
                "Architect",
                "234234324",
                "some url");

        employeeServiceTest.save(employee);

        ArgumentCaptor<Employee> employeeArgumentCaptor =
                ArgumentCaptor.forClass(Employee.class);

        verify(employeeRepository).save(employeeArgumentCaptor.capture());

        Employee capturedEmployee = employeeArgumentCaptor.getValue();
        assertThat(capturedEmployee).isEqualTo(employee);
    }

    @Test
    void givenEmployeeWithExistingEmail_whenSave_ThenThrowException() {
        Employee employee = new Employee(
                "Robert",
                "Martin",
                "martin@gmail.com",
                "Architect",
                "234234324",
                "some url");
        employee.setId(1L);
        Employee employee2 = new Employee(
                "Robert",
                "Martin",
                "martin@gmail.com",
                "Architect",
                "234234324",
                "some url");
        employee2.setId(2L);

        given(employeeRepository.findByEmail(employee.getEmail())).willReturn(Optional.of(employee2));

        assertThatThrownBy(() -> employeeServiceTest.save(employee))
                .isInstanceOf(BadRequestException.class)
                .hasMessageContaining(format("Employee with email %s already exist", employee.getEmail()));

        verify(employeeRepository, never()).save(employee);
    }

    @Test
    void givenEmployeeWithExistingPhoneNumber_whenSave_ThenThrowException() {
        Employee employee = new Employee(
                "Robert",
                "Martin",
                "martin@gmail.com",
                "Architect",
                "234234324",
                "some url");
        employee.setId(1L);
        Employee employee2 = new Employee(
                "Robert",
                "Martin",
                "martin@gmail.com",
                "Architect",
                "234234324",
                "some url");
        employee2.setId(2L);

        given(employeeRepository.findByPhone(employee.getPhone())).willReturn(Optional.of(employee2));

        assertThatThrownBy(() -> employeeServiceTest.save(employee))
                .isInstanceOf(BadRequestException.class)
                .hasMessageContaining(format("Employee with phone %s already exist", employee.getPhone()));

        verify(employeeRepository, never()).save(employee);
    }

    @Test
    void whenGetAll_thenReturn() {
        employeeServiceTest.getAll();

        verify(employeeRepository).findAll();
    }

    @Test
    void givenExistingId_whenGetById_thenReturnEmployee() {
        Employee employee = new Employee(
                "Robert",
                "Martin",
                "martin@gmail.com",
                "Architect",
                "234234324",
                "some url");

        given(employeeRepository.findById(any())).willReturn(Optional.of(employee));

        Employee actual = employeeServiceTest.getById(any());

        assertThat(actual).isEqualTo(employee);

        verify(employeeRepository).findById(any());
    }

    @Test
    void givenMotExistingId_whenGetById_thenThrowException() {
        Long id = 10L;

        given(employeeRepository.findById(id)).willReturn(Optional.empty());

        assertThatThrownBy(() -> employeeServiceTest.getById(id))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining(format("User by id = %s was not found", id));
    }

    @Test
    void givenExistingId_whenDelete_thenDeleted() {
        Long id = 1L;

        given(employeeRepository.existsById(id)).willReturn(true);

        employeeServiceTest.delete(id);

        verify(employeeRepository).deleteById(id);
    }

    @Test
    void givenNotExistingId_whenDelete_thenThrowException() {
        Long id = 1L;

        given(employeeRepository.existsById(id)).willReturn(false);

        assertThatThrownBy(() -> employeeServiceTest.delete(id))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining(format("User by id = %s was not found", id));

        verify(employeeRepository, never()).deleteById(id);
    }
}