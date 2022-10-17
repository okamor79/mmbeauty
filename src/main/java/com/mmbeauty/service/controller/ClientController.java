package com.mmbeauty.service.controller;

import com.mmbeauty.service.model.Client;
import com.mmbeauty.service.service.ClientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/client")
public class ClientController {

    private final ClientsService clientsService;

    @Autowired
    public ClientController(ClientsService clientsService) {
        this.clientsService = clientsService;
    }

    @GetMapping("/login/{login}/{password}")
    public ResponseEntity<Void> ClientLogin(@PathVariable("login") String login, @PathVariable("password") String password) {
        return clientsService.clientLogin(login, password) ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @PostMapping("/register")
    public Client registerClient(@RequestBody Client client) {
        return clientsService.addClient(client);
    }

    @GetMapping("/list")
    public List<Client> getAllList() {
        return clientsService.getAllClients();
    }

    @GetMapping("/info/{id}")
    public Optional<Client> getClientInfo(@PathVariable("id") Long id) {
        return clientsService.getClientById(id);
    }

    @PostMapping("/edit")
    public Client editClient(@RequestBody Client client) {
        return clientsService.editClient(client);
    }
}
