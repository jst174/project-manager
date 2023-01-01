package org.stepanenko.projectmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.stepanenko.projectmanager.model.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
