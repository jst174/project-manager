package org.stepanenko.projectmanager.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.stepanenko.projectmanager.model.Client;
import org.stepanenko.projectmanager.repository.ClientRepository;

import javax.persistence.EntityNotFoundException;

import static java.lang.String.format;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ClientServiceTest {

    @Mock
    private ClientRepository clientRepository;
    @InjectMocks
    private ClientService clientService;

    @Test
    void givenClient_whenSave_thenSaved() {
        clientService.save(TestData.client1);

        ArgumentCaptor<Client> clientArgumentCaptor =
                ArgumentCaptor.forClass(Client.class);

        verify(clientRepository).save(clientArgumentCaptor.capture());

        Client capturedClient = clientArgumentCaptor.getValue();
        assertThat(capturedClient).isEqualTo(TestData.client1);

    }

    @Test
    void whenGetAll_thenReturnAllClients() {
        clientService.getAll();

        verify(clientRepository).findAll();
    }

    @Test
    void getById() {
    }

    @Test
    void givenExistingId_whenDelete_thenDeleted() {
        Long id = 1L;

        given(clientRepository.existsById(id)).willReturn(true);

        clientService.delete(id);

        verify(clientRepository).deleteById(id);
    }

    @Test
    void givenNotExistingId_whenDelete_thenThrowException() {
        Long id = 1L;

        given(clientRepository.existsById(id)).willReturn(false);

        assertThatThrownBy(() -> clientService.delete(id))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining(format("Client with id = %s was not found", id));

        verify(clientRepository, never()).deleteById(id);
    }

    interface TestData {
        Client client1 = Client.builder()
                .id(1L)
                .name("Brusnika")
                .build();
        Client client2 = Client.builder()
                .id(2L)
                .name("Atom")
                .build();
    }
}