package com.mmbeauty.service.model;

import javax.persistence.*;

import lombok.*;


import java.util.Date;
import java.util.List;

@Entity
@Table(name = "course")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String descript;

    private String shortLink;

    private String link;

    private double price;

    private Date createdDate = new Date();

    private int duration;

    private double discont = 0;

    private int status;


}
