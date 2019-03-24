package com.github.fish56.springdatajpa.entity;

import javax.persistence.*;

@Entity
public class Monkey {
    @Id
    private Long id;

    private String name;
}
