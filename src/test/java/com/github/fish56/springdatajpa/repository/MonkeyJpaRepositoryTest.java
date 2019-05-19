package com.github.fish56.springdatajpa.repository;

import com.alibaba.fastjson.JSONObject;
import com.github.fish56.springdatajpa.SpringDataJpaImplApplicationTests;
import com.github.fish56.springdatajpa.entity.Monkey;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;

import java.util.List;

import static org.junit.Assert.*;

/**
 * findAllByExample就是寻找所有符合条件的记录
 */
public class MonkeyJpaRepositoryTest extends SpringDataJpaImplApplicationTests {
    @Autowired
    private MonkeyJpaRepository monkeyJpaRepository;

    @Before
    public void init(){
        for (Long i = 0L; i < 10; i++) {
            Monkey monkey = new Monkey();
            monkey.setId(i);
            monkey.setName("monkey" + i);
            monkey.setProfile("profile...");
            monkeyJpaRepository.save(monkey);
        }
    }

    @Test
    public void findAll(){
        Monkey monkey = new Monkey();
        monkey.setName("monkey3");
        Example<Monkey> monkeyExample = Example.of(monkey);

        List<Monkey> monkeys = monkeyJpaRepository.findAll(monkeyExample);
        System.out.println(JSONObject.toJSONString(monkeys));
        // [{"id":3,"name":"monkey3","profile":"profile..."}]
    }

    @Test
    public void findAll2(){
        Monkey monkey = new Monkey();
        monkey.setProfile("profile...");
        Example<Monkey> monkeyExample = Example.of(monkey);

        List<Monkey> monkeys = monkeyJpaRepository.findAll(monkeyExample);
        System.out.println(JSONObject.toJSONString(monkeys));
        // ... 所有列表
    }
}