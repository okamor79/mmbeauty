package com.mmbeauty.service.service;

import com.liqpay.LiqPay;
import com.mmbeauty.service.model.Client;
import com.mmbeauty.service.model.Course;
import com.mmbeauty.service.model.EMail;
import com.mmbeauty.service.model.Sale;
import com.mmbeauty.service.repository.ClientsRepository;
import com.mmbeauty.service.repository.CourseRepository;
import com.mmbeauty.service.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ClientsServiceImpl implements ClientsService {


    @Value("${mail.from}")
    private String emailFrom;
    @Autowired
    private final JavaMailSender javaMailSender;

    @Autowired
    private final CourseRepository courseRepository;

    @Autowired
    private final SaleRepository saleRepository;

    @Autowired
    private final ClientsRepository clientsRepository;
    private static final String PUBLIC_KEY = "sandbox_i95838346916";
    private static final String PRIVATE_KEY = "sandbox_LNqBdEb7fiJILtTy1hNxFXidEY2asDbUMeHke60O";

    public ClientsServiceImpl(JavaMailSender javaMailSender, CourseRepository courseRepository, SaleRepository saleRepository, ClientsRepository clientsRepository) {
        this.javaMailSender = javaMailSender;
        this.courseRepository = courseRepository;
        this.saleRepository = saleRepository;
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

    @Override
    public String paymentForm(int id) {
        UUID uuid = UUID.randomUUID();
        String orderID = uuid.toString();
        System.out.println(orderID);
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("action", "pay");
        params.put("amount", "500");
        params.put("currency", "UAH");
        params.put("description", "Оплата курсу");
        params.put("order_id", orderID);
        params.put("version", "3");
        LiqPay liqpay = new LiqPay(PUBLIC_KEY, PRIVATE_KEY);
        String html = liqpay.cnb_form(params);

        Sale sale = new Sale();
        Client client = clientsRepository.getClientById(id);
        int courseID = 1;
        Course course = courseRepository.getCourseById((long) courseID);
        sale.setClient(client);
        sale.setOrderid(orderID);
        sale.setCourse(course);
        sale.setDateBuy(new Date());
        System.out.println(sale.toString());
        saleRepository.save(sale);
        System.out.println(html);
        return html;
    }

    public void sendMail(EMail mail) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(mail.getTo());
        msg.setFrom(mail.getFrom());
        msg.setSubject(mail.getSubject());
        msg.setText(mail.getContent());
        javaMailSender.send(msg);
    }

    @Override
    @Scheduled(cron = "*/5 * * * * *")
    public void checkPayment() throws Exception {
        LiqPay liqpay = new LiqPay(PUBLIC_KEY, PRIVATE_KEY);
        List<Sale> sales = saleRepository.getAllUnpaidOrder();
        for (int i = 0; i<sales.size(); i++) {
            HashMap<String, String> params = new HashMap<String, String>();
            //System.out.println(sales.get(i));
            params.put("action", "status");
            params.put("version", "3");
            params.put("order_id", sales.get(i).getOrderid());
            HashMap<String, Object> res = (HashMap<String, Object>) liqpay.api("request", params);
            if (res.get("status").equals("success")) {
                Course course = courseRepository.getCourseById(sales.get(i).getCourse().getId());
                sales.get(i).setStatus(true);
                String d =  new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date((Long) res.get("end_date")));
                Date payDate = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse(d);
                int durationDate = course.getDuration();
                Date expDate = new Date();
                expDate.setTime(payDate.getTime() + (long) durationDate *1000*60*60*24);
                sales.get(i).setDatePayment(payDate);
                sales.get(i).setExpireDate(expDate);
                sales.get(i).setPayCheck(true);
                sales.get(i).setCheckCode(String.valueOf(res.get("payment_id")));
                saleRepository.save(sales.get(i));
            }
            params.clear();
        }



    }


}
