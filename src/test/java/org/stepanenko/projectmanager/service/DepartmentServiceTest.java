package org.stepanenko.projectmanager.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.stepanenko.projectmanager.exceptions.BadRequestException;
import org.stepanenko.projectmanager.model.Department;
import org.stepanenko.projectmanager.repository.DepartmentRepository;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

import static java.lang.String.format;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceTest {

    @Mock
    private DepartmentRepository departmentRepository;
    @InjectMocks
    private DepartmentService departmentService;

    @Test
    void givenNewDepartment_whenSave_thenSaved() {
        departmentService.save(TestData.department1);

        ArgumentCaptor<Department> departmentArgumentCaptor =
                ArgumentCaptor.forClass(Department.class);

        verify(departmentRepository).save(departmentArgumentCaptor.capture());

        Department capturedDepartment = departmentArgumentCaptor.getValue();

        assertThat(capturedDepartment).isEqualTo(TestData.department1);
    }

    @Test
    void givenExistingDepartmentName_whenSave_thenThrowException() {
        given(departmentRepository.findByName(TestData.department1.getName()))
                .willReturn(Optional.of(TestData.department2));

        assertThatThrownBy(() -> departmentService.save(TestData.department1))
                .isInstanceOf(BadRequestException.class)
                .hasMessageContaining(format("Department with name %s already exist", TestData.department1.getName()));

        verify(departmentRepository, never()).save(TestData.department1);
    }

    @Test
    void whenGetAll_thenReturnAllDepartments() {
        departmentService.getAll();

        verify(departmentRepository).findAll();
    }

    @Test
    void givenExistingId_whenGetById_thenReturnDepartment() {
        Long id = 1L;

        given(departmentRepository.findById(id)).willReturn(Optional.of(TestData.department1));

        Department actual = departmentService.getById(id);

        assertThat(actual).isEqualTo(TestData.department1);

        verify(departmentRepository).findById(id);
    }

    void givenNotExistingId_whenGetById_thenThrowException(){
        Long id = 2L;

        given(departmentRepository.findById(id)).willReturn(Optional.empty());

        assertThatThrownBy(()-> departmentService.getById(id))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining(format("Department with id = %s was not found", id));
    }

    @Test
    void givenExistingId_whenDelete_thenDeleted() {
        Long id = 1L;

        given(departmentRepository.existsById(id)).willReturn(true);

        departmentService.delete(id);

        verify(departmentRepository).deleteById(id);
    }

    @Test
    void givenNotExistingId_whenDelete_thenThrowException() {
        Long id = 1L;

        given(departmentRepository.existsById(id)).willReturn(false);

        assertThatThrownBy(() -> departmentService.delete(id))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining(format("Department with id = %s was not found", id));

        verify(departmentRepository, never()).deleteById(id);
    }


    interface TestData {
        Department department1 = Department.builder()
                .id(1L)
                .name("AR")
                .build();
        Department department2 = Department.builder()
                .id(2L)
                .name("HVAC")
                .build();
    }
}