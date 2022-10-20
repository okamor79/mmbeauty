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
    public ResponseEntity<Void> registerClient(@RequestBody Client client) {
        clientsService.addClient(client);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/list")
    public List<Client> getAllList() {
        return clientsService.getAllClients();
    }

    @GetMapping("/info/{email}")
    public Optional<Client> getClientInfo(@PathVariable("email") String email) {
        return clientsService.getClientByEmail(email);
    }

    @PostMapping("/edit")
    public Client editClient(@RequestBody Client client) {
        return clientsService.editClient(client);
    }
}
