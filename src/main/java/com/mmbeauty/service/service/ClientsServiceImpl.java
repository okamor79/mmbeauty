package com.mmbeauty.service.service;

import com.liqpay.LiqPay;
import com.mmbeauty.service.model.Client;
import com.mmbeauty.service.model.EMail;
import com.mmbeauty.service.repository.ClientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.*;

@Service
public class ClientsServiceImpl implements ClientsService {


    @Value("${mail.from}")
    private String emailFrom;
    @Autowired
    private final JavaMailSender javaMailSender;

    @Autowired
    private final ClientsRepository clientsRepository;
    private static final String PUBLIC_KEY = "sandbox_i95838346916";
    private static final String PRIVATE_KEY = "sandbox_LNqBdEb7fiJILtTy1hNxFXidEY2asDbUMeHke60O";

    public ClientsServiceImpl(JavaMailSender javaMailSender, ClientsRepository clientsRepository) {
        this.javaMailSender = javaMailSender;
        this.clientsRepository = clientsRepository;
    }

    public static String generateRandomPassword(int length) {
        final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        SecureRandom rnd = new SecureRandom();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int rndIndex = rnd.nextInt(chars.length());
            sb.append(chars.charAt(rndIndex));
        }
        return sb.toString();
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
    public int addClient(Client client) {
        int status = 0;
        Client clientInfo = clientsRepository.getClientByEmail(client.getEmail());
        if (clientInfo == null) {
            client.setCreated(new Date());
            clientsRepository.save(client);
        } else
            status = -1;
        return status;
    }

    @Override
    public Client editClient(Client client) {
        client.setModify(new Date());
        return clientsRepository.save(client);
    }

    @Override
    public Optional<Client> resetPassword(String email) {
        Client client = clientsRepository.getClientByEmail(email);
        EMail mail = new EMail();
        if (client == null) return null;
        String newPassword = generateRandomPassword(10);
        String passBase64 = Base64.getEncoder().encodeToString(newPassword.getBytes());
        client.setPassword(passBase64);
        clientsRepository.save(client);
        mail.setTo(client.getEmail());
        mail.setContent(newPassword);
        mail.setFrom(emailFrom);
        sendMail(mail);


        return Optional.ofNullable(clientsRepository.getClientByEmail(email));
    }

    public void sendMail(EMail mail) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(mail.getTo());
        msg.setFrom(mail.getFrom());
        msg.setSubject(mail.getSubject());
        msg.setText(mail.getContent());
        javaMailSender.send(msg);
    }
}
