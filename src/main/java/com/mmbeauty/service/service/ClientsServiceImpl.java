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
    public Boolean clientLogin(String login, String password) {
        Client clientInfo = clientsRepository.getClientByEmail(login);
        if (clientInfo == null) return false;
        return clientInfo.getEmail().contentEquals(login) ? (clientInfo.getPassword().contentEquals(password) ? true : false) : false;
    }

    @Override
    public List<Client> getAllClients() {
        return clientsRepository.findAll();
    }

    @Override
    public Optional<Client> getClientById(Long id) {
        return clientsRepository.findById(id);
    }

    @Override
    public Client addClient(Client client) {
        client.setCreated(new Date());
        return clientsRepository.save(client);
    }

    @Override
    public Client editClient(Client client) {
        client.setModify(new Date());
        return clientsRepository.save(client);
    }
}
