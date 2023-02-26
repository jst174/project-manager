package org.stepanenko.projectmanager.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.stepanenko.projectmanager.model.Client;
import org.stepanenko.projectmanager.service.ClientService;
import org.stepanenko.projectmanager.util.CheckValidationErrors;
import org.stepanenko.projectmanager.util.validator.ClientValidator;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/clients")
public class ClientController {

    private final ClientService clientService;
    private final ClientValidator clientValidator;

    public ClientController(ClientService clientService, ClientValidator clientValidator) {
        this.clientService = clientService;
        this.clientValidator = clientValidator;
    }

    @CrossOrigin
    @GetMapping
    public ResponseEntity<List<Client>> getAllClients() {
        List<Client> clients = clientService.getAll();
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("{id}")
    public ResponseEntity<Client> getClient(@PathVariable Long id) {
        Client client = clientService.getById(id);
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping
    public ResponseEntity<Client> createClient(@RequestBody @Valid Client client,
                                               BindingResult bindingResult) {
        clientValidator.validate(client, bindingResult);
        CheckValidationErrors.check(bindingResult);
        Client newClient = clientService.save(client);
        return new ResponseEntity<>(newClient, HttpStatus.CREATED);
    }

    @CrossOrigin
    @PutMapping
    public ResponseEntity<Client> updateClient(@RequestBody @Valid Client client,
                                               BindingResult bindingResult) {
        clientValidator.validate(client, bindingResult);
        CheckValidationErrors.check(bindingResult);
        Client updatedClient = clientService.save(client);
        return new ResponseEntity<>(updatedClient, HttpStatus.OK);
    }

    @CrossOrigin
    @DeleteMapping("{id}")
    public ResponseEntity<Client> deleteClient(@PathVariable Long id) {
        clientService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
