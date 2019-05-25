package com.github.fish56.springdatajpa.repository;

import com.alibaba.fastjson.JSONObject;
import com.github.fish56.springdatajpa.SpringDataJpaImplApplicationTests;
import com.github.fish56.springdatajpa.entity.Monkey;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertTrue;

public class MonkeyRepositoryTest extends SpringDataJpaImplApplicationTests {
    @Autowired
    private MonkeyExampleRepository repository;

    /**
     * 在每个测试用例启动前初始化环境
     */
    @Before
    public void init(){
        for (int i = 0; i < 10; i++) {
            Monkey monkey = new Monkey();
            monkey.setName("Monkey" + i);
            monkey.setProfile("profile");
            monkey.setBirthDay(new Date());
            repository.save(monkey);
        }

    }

    @Test
    public void insert(){
        Monkey monkey = new Monkey();
        monkey.setProfile("profile");
        Example<Monkey> monkeyExample = Example.of(monkey);

        Page<Monkey> monkeys = repository.findAll(monkeyExample, PageRequest.of(2, 2));
        System.out.println(JSONObject.toJSONString(monkeys.iterator()));
    }

}