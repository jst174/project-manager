package org.stepanenko.projectmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.stepanenko.projectmanager.model.Project;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    List<Project> findProjectsByEmployees_Id(Long id);
}
