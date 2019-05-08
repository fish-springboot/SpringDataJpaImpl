package com.github.fish56.springdatajpa.repository;

import com.github.fish56.springdatajpa.entity.Monkey;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MonkeyJPQL extends CrudRepository<Monkey, Long> {
    // 查询出所有的记录 select * from monkey
    @Query("select m from Monkey m")
    public List<Monkey> findAllMonkeyByQuery();

    // ?1 表明这是第一个参数的占位符，% 是SQL语句的
    @Query("select m from Monkey m where m.name like ?1%")
    public List<Monkey> findAllMonkeyNameBeginWith(String beginString);

    // 原生SQL语句，查询到结果后会做一次映射
    @Query(value = "select * from monkey where id > ?1", nativeQuery = true)
    public List<Monkey> findMonkeyWithNativeSQL(Long id);
}
