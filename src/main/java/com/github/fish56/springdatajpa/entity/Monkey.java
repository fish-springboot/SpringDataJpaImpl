package com.github.fish56.springdatajpa.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "monkey")
public class Monkey {
    @Id
    @Column(columnDefinition = "int(10)")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 20)
    private String name;

    @Column(name = "birth_day")
    @Temporal(TemporalType.DATE)
    private Date birthDay;
}

