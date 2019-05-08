package com.github.fish56.springdatajpa.repository;

import com.alibaba.fastjson.JSONObject;
import com.github.fish56.springdatajpa.SpringDataJpaImplApplicationTests;
import com.github.fish56.springdatajpa.entity.Fish;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import static org.junit.Assert.*;

public class FishRepositoryTest extends SpringDataJpaImplApplicationTests {
    @Autowired
    private FishRepository fishRepository;
    private Fish fish1;

    @Before
    public void init(){
        fish1 = new Fish();
    }

    // 试图插入重复值的时候
    @Test
    public void saveUnique(){
        fish1.setName("Jon");
        Fish fish = fishRepository.save(fish1);
        System.out.println(JSONObject.toJSONString(fish));

        fish = fishRepository.save(fish1);
        System.out.println(JSONObject.toJSONString(fish));

        // Hibernate: insert into fish (id, name) values (null, ?)
        // {"id":1,"name":"Jon"}
        // Hibernate: select fish0_.id as id1_2_0_, fish0_.name as name2_2_0_ from fish fish0_ where fish0_.id=?
        // {"id":1,"name":"Jon"}
    }

    // 抛出运行时异常
    @Test(expected = DataIntegrityViolationException.class)
    public void saveNull(){
        Fish fish = fishRepository.save(fish1);
        System.out.println(JSONObject.toJSONString(fish));
    }
}