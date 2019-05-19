package com.github.fish56.springdatajpa.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class Panda {
    @Id // 主键是必须指定的
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;

    @Transient // 不映射到数据库表的字段
    private String fullName;

    @Temporal(TemporalType.DATE) // 映射为Date类型，而不是默认的DateTime
    private Date birthDay;

    @OneToOne
    private Wallet wallet;
}
// create table panda (id integer not null, date date,
//  first_name varchar(255), last_name varchar(255), primary key (id))