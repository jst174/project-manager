package org.stepanenko.projectmanager.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.stepanenko.projectmanager.model.Client;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ClientRepositoryTest {

    @Autowired
    private ClientRepository clientRepository;

    @Test
    void givenExistentClientName_whenFindByName_ThenReturn() {
        String name = "Brusnika";
        Client client = new Client(name);

        clientRepository.save(client);

        Client existClient = clientRepository.findByName(name).get();

        assertEquals(client, existClient);

    }
}