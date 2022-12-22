package com.mmbeauty.service.model;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Sale {

    @Id
    private Long id;

    @OneToOne
    @JoinColumn
    private Client client;

    @OneToOne
    @JoinColumn
    private Course course;

    private Date dateBuy;

    private Date datePayment;

    private boolean payCheck;

    private String checkCode;

    private boolean status;

    private Date expireDate;


}
