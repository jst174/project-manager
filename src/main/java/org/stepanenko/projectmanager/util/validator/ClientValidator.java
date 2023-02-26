package org.stepanenko.projectmanager.util.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.stepanenko.projectmanager.model.Client;
import org.stepanenko.projectmanager.repository.ClientRepository;

import java.util.Objects;

@Component
public class ClientValidator implements Validator {

    private final ClientRepository clientRepository;

    public ClientValidator(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Client.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Client client = (Client) target;
        if (clientRepository.findByName(client.getName())
                .filter(e -> !Objects.equals(e.getId(), client.getId()))
                .isPresent()) {
            errors.rejectValue("name", "", "Client with this name already exist");
        }
    }
}
