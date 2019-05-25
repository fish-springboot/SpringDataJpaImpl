package com.github.fish56.springdatajpa.one2on2;

import com.alibaba.fastjson.JSONObject;
import com.github.fish56.springdatajpa.SpringDataJpaImplApplicationTests;
import com.github.fish56.springdatajpa.one2one.Panda;
import com.github.fish56.springdatajpa.one2one.PandaRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

public class PandaRepositoryTest extends SpringDataJpaImplApplicationTests {
    @Autowired
    private PandaRepository repository;

    private Panda panda1 = new Panda();
    private Panda panda2 = new Panda();

    @Before
    public void init(){
        panda1.setLastName("Snow");
        panda2.setLastName("Jack");
    }

    @Test
    public void find(){
        repository.save(panda1);
        Panda panda = repository.findByFirstNameOrLastName("Jon", "Snow");
        System.out.println(JSONObject.toJSONString(panda));
        // {"id":1,"lastName":"Snow"}
    }

    @Test
    public void distinct(){
        panda1.setBirthDay(new Date(new Date().getTime() + 10000));
        panda2.setBirthDay(new Date(new Date().getTime() - 10000));
        repository.save(panda1);
        repository.save(panda2);
        List<Panda> pandas = repository.findByBirthDayBefore(new Date());
        System.out.println(JSONObject.toJSONString(pandas));
    }

    // 重复插入时自动忽略id的值
    @Test
    public void saveTwice(){
        panda1.setId(2L);
        Panda panda = repository.save(panda1);
        System.out.println(JSONObject.toJSONString(panda));

        panda =  repository.save(panda1);
        System.out.println(JSONObject.toJSONString(panda));
        // Hibernate: insert into panda (id, birth_day, first_name, last_name) values (null, ?, ?, ?)
        // {"id":2,"lastName":"Snow"}
    }
}