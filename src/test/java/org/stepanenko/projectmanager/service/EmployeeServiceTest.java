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
        employeeServiceTest.save(TestData.employee1);

        ArgumentCaptor<Employee> employeeArgumentCaptor =
                ArgumentCaptor.forClass(Employee.class);

        verify(employeeRepository).save(employeeArgumentCaptor.capture());

        Employee capturedEmployee = employeeArgumentCaptor.getValue();
        assertThat(capturedEmployee).isEqualTo(TestData.employee1);
    }

    @Test
    void givenEmployeeWithExistingEmail_whenSave_ThenThrowException() {
        given(employeeRepository.findByEmail(TestData.employee1.getEmail())).willReturn(Optional.of(TestData.employee2));

        assertThatThrownBy(() -> employeeServiceTest.save(TestData.employee1))
                .isInstanceOf(BadRequestException.class)
                .hasMessageContaining(format("Employee with email %s already exist", TestData.employee1.getEmail()));

        verify(employeeRepository, never()).save(TestData.employee1);
    }

    @Test
    void givenEmployeeWithExistingPhoneNumber_whenSave_ThenThrowException() {
        given(employeeRepository.findByPhone(TestData.employee1.getPhone())).willReturn(Optional.of(TestData.employee2));

        assertThatThrownBy(() -> employeeServiceTest.save(TestData.employee1))
                .isInstanceOf(BadRequestException.class)
                .hasMessageContaining(format("Employee with phone %s already exist", TestData.employee1.getPhone()));

        verify(employeeRepository, never()).save(TestData.employee1);
    }

    @Test
    void whenGetAll_thenReturn() {
        employeeServiceTest.getAll();

        verify(employeeRepository).findAll();
    }

    @Test
    void givenExistingId_whenGetById_thenReturnEmployee() {
        given(employeeRepository.findById(any())).willReturn(Optional.of(TestData.employee1));

        Employee actual = employeeServiceTest.getById(any());

        assertThat(actual).isEqualTo(TestData.employee1);

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

    interface TestData {
        Employee employee1 = Employee.builder()
                .id(1L)
                .firstName("Robert")
                .lastName("Martin")
                .email("martin@gmail.com")
                .jobTitle("Architect")
                .phone("234234324")
                .imageUrl("some url")
                .build();

        Employee employee2 = Employee.builder()
                .id(2L)
                .firstName("George")
                .lastName("Martin")
                .email("martin@gmail.com")
                .jobTitle("Writer")
                .phone("234234324")
                .imageUrl("some url")
                .build();
    }
}