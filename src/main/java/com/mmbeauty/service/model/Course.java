package com.mmbeauty.service.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String descript;

    private String shortLink;

    private String link;

    private double price;

    private Date createdDate = new Date();

    private int duration;

    private double discont = 0;

    private int status;

    @OneToMany(mappedBy = "course")
    private List<Comment> comment;

}
