package org.stepanenko.projectmanager.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.stepanenko.projectmanager.exceptions.BadRequestException;
import org.stepanenko.projectmanager.model.Department;
import org.stepanenko.projectmanager.model.Project;
import org.stepanenko.projectmanager.repository.ProjectRepository;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

import static java.lang.String.format;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ProjectServiceTest {

    @Mock
    private ProjectRepository projectRepository;
    @InjectMocks
    private ProjectService projectService;

    @Test
    void givenNewProject_whenSave_thenSaved() {
        projectService.save(TestData.project1);

        ArgumentCaptor<Project> projectArgumentCaptor =
                ArgumentCaptor.forClass(Project.class);

        verify(projectRepository).save(projectArgumentCaptor.capture());

        Project capturedProject = projectArgumentCaptor.getValue();

        assertThat(capturedProject).isEqualTo(TestData.project1);
    }

    @Test
    void whenGetAll_thenReturnAllProjects() {
        projectService.getAll();

        verify(projectRepository).findAll();
    }

    @Test
    void givenExistingId_whenGetById_thenReturnProject() {
        Long id = 1L;

        given(projectRepository.findById(id)).willReturn(Optional.of(TestData.project1));

        Project actual = projectService.getById(id);

        assertThat(actual).isEqualTo(TestData.project1);

        verify(projectRepository).findById(id);
    }

    @Test
    void givenNotExistingId_whenGetById_thenThrowException() {
        Long id = 2L;

        given(projectRepository.findById(id)).willReturn(Optional.empty());

        assertThatThrownBy(() -> projectService.getById(id))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining(format("Project with id = %s was not found", id));
    }

    @Test
    void givenExistingId_whenDelete_thenDeleted() {
        Long id = 1L;

        given(projectRepository.existsById(id)).willReturn(true);

        projectService.delete(id);

        verify(projectRepository).deleteById(id);
    }

    @Test
    void givenNotExistingId_whenDelete_thenThrowException() {
        Long id = 1L;

        given(projectRepository.existsById(id)).willReturn(false);

        assertThatThrownBy(() -> projectService.delete(id))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining(format("Project with id = %s was not found", id));

        verify(projectRepository, never()).deleteById(id);
    }


    interface TestData {
        Project project1 = Project.builder()
                .id(1L)
                .name("Living house")
                .build();
        Project project2 = Project.builder()
                .id(2L)
                .name("Mall")
                .build();
    }
}
