package com.github.fish56.springdatajpa.one2many;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String content;

    @ManyToOne(cascade = CascadeType.ALL)
    private Article article;
}
