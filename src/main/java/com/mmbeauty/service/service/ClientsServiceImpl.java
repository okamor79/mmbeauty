package com.mmbeauty.service.service;

import com.mmbeauty.service.model.Client;
import com.mmbeauty.service.repository.ClientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ClientsServiceImpl implements ClientsService {

    private final ClientsRepository clientsRepository;

    @Autowired
    public ClientsServiceImpl(ClientsRepository clientsRepository) {
        this.clientsRepository = clientsRepository;
    }

    @Override
    public Client clientLogin(String login, String password) {
        Client clientInfo = clientsRepository.getClientByEmail(login);
        if (clientInfo == null) return null;
        return clientInfo.getEmail().contentEquals(login) ? (clientInfo.getPassword().contentEquals(password) ? clientInfo : null) : null;

    }

    @Override
    public List<Client> getAllClients() {
        return clientsRepository.findAll();
    }

    @Override
    public Optional<Client> getClientByEmail(String email) {
        return Optional.ofNullable(clientsRepository.getClientByEmail(email));
    }

    @Override
    public void addClient(Client client) {
        client.setCreated(new Date());
        clientsRepository.save(client);
    }

    @Override
    public Client editClient(Client client) {
        client.setModify(new Date());
        return clientsRepository.save(client);
    }
}
