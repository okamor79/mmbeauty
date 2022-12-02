package com.mmbeauty.service.service;

import com.mmbeauty.service.model.Client;

import java.util.List;
import java.util.Optional;

public interface ClientsService {

    Client clientLogin(String login, String password);

    List<Client> getAllClients();

    Optional<Client> getClientByEmail(String email);

    int addClient(Client client);

    Client editClient(Client client);

}
