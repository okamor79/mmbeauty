package com.mmbeauty.service.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String textComment;

    @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm")
    private Date created = new Date();

    @ManyToOne
    private Course course;

    @ManyToOne
    private Client client;
}
