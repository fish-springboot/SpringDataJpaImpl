package com.github.fish56.springdatajpa.crud;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Data
@ToString
@Entity
final public class Fish {
    @Id // 主键是必须指定的
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;
}
// create table fish (id bigint not null auto_increment, name varchar(255) not null, primary key (id))