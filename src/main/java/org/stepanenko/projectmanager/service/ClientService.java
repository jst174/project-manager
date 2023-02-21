package org.stepanenko.projectmanager.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.stepanenko.projectmanager.exceptions.BadRequestException;
import org.stepanenko.projectmanager.model.Client;
import org.stepanenko.projectmanager.repository.ClientRepository;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

import static java.lang.String.format;

@Slf4j
@Service
@Transactional
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client save(Client client) {
        log.info("Save client {}", client.getName());
        verifyNameUnique(client);
        return clientRepository.save(client);
    }

    public List<Client> getAll() {
        log.info("Get all clients");
        return clientRepository.findAll();
    }

    public Client getById(Long id) {
        log.info("Get client by id = {}", id);
        return clientRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(format("Client with id = %s was not found", id)));
    }

    public void delete(Long id) {
        log.info("Delete client with id = {}", id);
        if (!clientRepository.existsById(id)) {
            throw new EntityNotFoundException(format("Client with id = %s was not found", id));
        }
        clientRepository.deleteById(id);
    }

    private void verifyNameUnique(Client client) {
        if (clientRepository.findByName(client.getName())
                .filter(e -> !Objects.equals(e.getId(), client.getId()))
                .isPresent()) {
            throw new BadRequestException(
                    format("Client with name %s already exist", client.getName()));
        }
    }
}
