package com.mmbeauty.service.model;

import com.mmbeauty.service.model.enums.Roles;
import com.mmbeauty.service.model.enums.Statuses;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Client")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String phone;

    private String name;

    private String password;

    private String email;

    @Enumerated(EnumType.STRING)
    private Roles role;

    @Enumerated(EnumType.STRING)
    private Statuses status;

    @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm")
    private Date created;

    @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm")
    private Date modify;

    @OneToMany(mappedBy = "client")
    private List<Comment> comment;

}
