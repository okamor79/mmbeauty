package com.mmbeauty.service.controller;

import com.mmbeauty.service.model.Client;
import com.mmbeauty.service.service.ClientsService;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/client")
@CrossOrigin(maxAge = 36000, allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST})
public class ClientController {

    private final ClientsService clientsService;

    @Autowired
    public ClientController(ClientsService clientsService) {
        this.clientsService = clientsService;
    }

    @GetMapping("/login/{login}/{password}")
    public ResponseEntity<Client> ClientLogin(@PathVariable("login") String login, @PathVariable("password") String password) {
        Client cl = clientsService.clientLogin(login, password);
        if (cl == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } else {
            return ResponseEntity.accepted().body(cl);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<Void> registerClient(@RequestBody Client client) {
        int register = clientsService.addClient(client);
        if (register != -1) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else
            return new ResponseEntity<>((HttpStatus.CONFLICT));
    }

    @GetMapping("/list")
    public List<Client> getAllList() {
        return clientsService.getAllClients();
    }

    @GetMapping("/info/{email}")
    public Optional<Client> getClientInfo(@PathVariable("email") String email) {
        return clientsService.getClientByEmail(email);
    }

    @GetMapping("/reset/{email}")
    public ResponseEntity<Void> resetPassword(@PathVariable("email") String email) {
        Optional<Client> cl = clientsService.resetPassword(email);
        if (cl == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } else {
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @PostMapping("/payment/{id}")
    public String payment(@PathVariable("id") int id) {
        return clientsService.paymentForm(id);
    }

    @PostMapping("/edit")
    public Client editClient(@RequestBody Client client) {
        return clientsService.editClient(client);
    }

    @GetMapping("/checkpay")
    public void getPaymentList() throws Exception {
        clientsService.checkPayment();

    }


}
