package com.github.fish56.springdatajpa.repository;

import com.alibaba.fastjson.JSONObject;
import com.github.fish56.springdatajpa.SpringDataJpaImplApplicationTests;
import com.github.fish56.springdatajpa.entity.Panda;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

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
}