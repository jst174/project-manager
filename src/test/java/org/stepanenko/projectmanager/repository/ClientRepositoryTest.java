package org.stepanenko.projectmanager.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.stepanenko.projectmanager.model.Client;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class ClientRepositoryTest {

    @Autowired
    private ClientRepository clientRepository;

    @Test
    public void givenExistingClientName_whenFindByName_thenReturn() {
        String name = "Brusnika";
        Client expected = new Client(name);

        clientRepository.save(expected);

        Client actual = clientRepository.findByName(name).get();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void givenNotExistingClientName_whenFindName_thenReturnEmpty() {
        String name = "Brusnika";

        Optional<Client> actual = clientRepository.findByName(name);

        assertThat(actual).isEqualTo(Optional.empty());
    }
}