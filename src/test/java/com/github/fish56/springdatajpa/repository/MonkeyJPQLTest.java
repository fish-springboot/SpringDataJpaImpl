package com.github.fish56.springdatajpa.repository;

import com.alibaba.fastjson.JSONObject;
import com.github.fish56.springdatajpa.SpringDataJpaImplApplicationTests;
import com.github.fish56.springdatajpa.entity.Monkey;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class MonkeyJPQLTest extends SpringDataJpaImplApplicationTests {
    @Autowired
    private MonkeyJPQL monkeyJPQL;

    private Monkey monkey1;
    private Monkey monkey2;
    private List<Monkey> monkeys;

    @Before
    public void init(){
        monkey1 = new Monkey();
        monkey1.setName("Bob");
        monkey2 = new Monkey();
        monkey2.setName("Peter");
        monkeys = new ArrayList<>();
    }

    @Test
    public void find(){
        monkeyJPQL.save(monkey1);
        monkeyJPQL.save(monkey2);

        monkeys = monkeyJPQL.findAllMonkeyByQuery();
        System.out.println(JSONObject.toJSONString(monkeys));
        // [{"id":1,"name":"Bob"},{"id":2,"name":"Peter"}]

        monkeys = monkeyJPQL.findAllMonkeyNameBeginWith("B");
        System.out.println(JSONObject.toJSONString(monkeys));
        // [{"id":1,"name":"Bob"}]

        monkeys = monkeyJPQL.findMonkeyWithNativeSQL(1L);
        System.out.println(JSONObject.toJSONString(monkeys));
        // [{"id":2,"name":"Peter"}]
    }
}