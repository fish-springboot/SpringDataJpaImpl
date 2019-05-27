package com.github.fish56.springdatajpa.one2many;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.List;

/**
 * 实体之间一对多的关系
 * 一个文章对应多个评论
 */
@Data
@Accessors(chain = true)
@Entity
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * CascadeType.ALL: 当插入article时，相关的comments也会被插入到数据库
     * JoinColumn 对应的外键
     */
    // @OneToMany(mappedBy = "article", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "comment_id")
    private List<Comment> comments;

    private String content;
}
// create table article (id bigint not null, content varchar(255), primary key (id))