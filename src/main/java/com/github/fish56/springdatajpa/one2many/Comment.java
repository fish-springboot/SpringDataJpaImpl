package com.github.fish56.springdatajpa.one2many;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Data
@Accessors(chain = true)
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String content;

    @ManyToOne(cascade = CascadeType.ALL)
    private Article article;
}
// create table comment (id bigint not null, content varchar(255), article_id bigint, primary key (id))