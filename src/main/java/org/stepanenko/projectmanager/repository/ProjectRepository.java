package org.stepanenko.projectmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.stepanenko.projectmanager.model.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
