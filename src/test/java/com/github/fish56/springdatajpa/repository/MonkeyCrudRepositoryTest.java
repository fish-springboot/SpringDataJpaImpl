package com.github.fish56.springdatajpa.repository;

import com.alibaba.fastjson.JSONObject;
import com.github.fish56.springdatajpa.SpringDataJpaImplApplicationTests;
import com.github.fish56.springdatajpa.entity.Monkey;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

public class MonkeyCrudRepositoryTest extends SpringDataJpaImplApplicationTests {
    @Autowired
    private MonkeyCrudRepository repository;

    /**
     * 声明一些变量，省的在每个测试用例中声明
     */
    private Monkey monkey1;
    private Monkey monkey2;
    private List<Monkey> monkeys;

    private Optional<Monkey> monkey;

    /**
     * 在每个测试用例启动前初始化环境
     */
    @Before
    public void init(){
        monkey1 = new Monkey();
        monkey1.setName("Jon");
        monkey2 = new Monkey();
        monkey2.setName("Peter");

        monkeys = new ArrayList<>();
        monkeys.add(monkey1);
        monkeys.add(monkey2);
    }

    @Test
    public void insert(){
        repository.save(monkey1);
        // {"id":1,"name":"Jon"} 同时返回了自增主键
        System.out.println(JSONObject.toJSONString(monkey1));

        // 查询
        monkey = repository.findById(1l);
        assertTrue(monkey.isPresent());
        System.out.println(JSONObject.toJSONString(monkey.get()));
        // {"id":1,"name":"Jon"}
    }

    @Test
    public void insertMany(){
        repository.saveAll(monkeys);
        System.out.println(JSONObject.toJSONString(monkeys));
        // [{"id":1,"name":"Jon"},{"id":2,"name":"Peter"}]
        // 同样拿到了自增主键

        Iterable<Monkey> monkeyList = repository.findAll();
        System.out.println(JSONObject.toJSONString(monkeyList));
        // [{"id":1,"name":"Jon"},{"id":2,"name":"Peter"}]

        List<Long> ids = new ArrayList<>();
        for (Monkey monkey : monkeyList){
            ids.add(monkey.getId());
        }
        ids.add(3L);
        Iterable<Monkey> monkeys = repository.findAllById(ids);
        System.out.println(JSONObject.toJSONString(monkeys));
        // [{"id":1,"name":"Jon"},{"id":2,"name":"Peter"}]

    }
}