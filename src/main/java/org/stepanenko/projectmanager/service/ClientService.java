package org.stepanenko.projectmanager.service;

import org.springframework.stereotype.Service;
import org.stepanenko.projectmanager.model.Client;
import org.stepanenko.projectmanager.repository.ClientRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;

import static java.lang.String.format;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client save(Client client) {
        return clientRepository.save(client);
    }

    public List<Client> getAll() {
        return clientRepository.findAll();
    }

    public Client getById(Long id) {
        return clientRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(format("Client by id = %s was not found", id)));
    }

    public void delete(Long id) {
        if (!clientRepository.existsById(id)) {
            throw new EntityNotFoundException(format("Client with id = %s was not found", id));
        }
        clientRepository.deleteById(id);
    }
}
