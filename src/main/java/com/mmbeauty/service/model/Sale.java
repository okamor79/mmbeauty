package com.mmbeauty.service.model;

import javax.persistence.*;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;


import java.util.Date;

@Entity
@Table(name = "Sale")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn
    private Client client;

    @OneToOne
    @JoinColumn
    private Course course;

    private String orderid;

    @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm")
    private Date dateBuy;

    @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm")
    private Date datePayment;

    private boolean payCheck;

    private String checkCode;

    private boolean status;

    @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm")
    private Date expireDate;


}
