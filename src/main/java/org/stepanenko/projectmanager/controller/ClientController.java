package org.stepanenko.projectmanager.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.stepanenko.projectmanager.model.Client;
import org.stepanenko.projectmanager.service.ClientService;

import java.util.List;

@RestController
@RequestMapping("api/v1/clients")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @CrossOrigin
    @GetMapping
    public ResponseEntity<List<Client>> getAllClients(){
        List<Client> clients = clientService.getAll();
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("{id}")
    public ResponseEntity<Client> getClient(@PathVariable Long id){
        Client client = clientService.getById(id);
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping
    public ResponseEntity<Client> createClient(@RequestBody Client client){
        Client newClient = clientService.create(client);
        return  new ResponseEntity<>(newClient, HttpStatus.CREATED);
    }

    @CrossOrigin
    @PutMapping
    public ResponseEntity<Client> updateClient(@RequestBody Client client){
        Client updatedClient = clientService.update(client);
        return new ResponseEntity<>(updatedClient, HttpStatus.OK);
    }

    @CrossOrigin
    @PutMapping("{id}")
    public ResponseEntity<Client> deleteClient(@PathVariable Long id){
        clientService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
