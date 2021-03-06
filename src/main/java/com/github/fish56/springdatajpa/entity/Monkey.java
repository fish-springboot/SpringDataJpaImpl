package com.github.fish56.springdatajpa.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Data
@ToString
@Entity // 将当前类作为实体映射到数据库
@Table(name = "monkey") // 对应到数据库的表名
public class Monkey {

    @Id // 作为表的主键，其实我觉得命名为PrimaryKey更加的合适
    @Column(columnDefinition = "smallint") // 手动指明对应的数据库数据类型
    @GeneratedValue(strategy=GenerationType.IDENTITY) // 设置字段生成策略为数据库的自增长字段
    private Long id;

    /**
     * 非空，非null，长度为20
     */
    @Column(unique = true, nullable = false, length = 20)
    private String name;

    /**
     * 自定义命名规则
     *   默认就是 驼峰命名->下换线命名
     */
    @Column(name = "birth_day")
    private Date birthDay;

    /**
     * 在第一次获取实体对象的时候不获取它的值，调用get方法的时候才获取
     */
    @Basic(fetch = FetchType.LAZY)
    private String profile;

    /**
     * 在insert语句的时候不包含这个字段，比如数据库中给了on update，那就可以把这里设置为false
     */
    @Column(insertable = false)
    private Date updateTime;
}
// create table monkey (id smallint not null auto_increment, birth_day datetime, name varchar(20) not null, profile varchar(255), update_time datetime, primary key (id))