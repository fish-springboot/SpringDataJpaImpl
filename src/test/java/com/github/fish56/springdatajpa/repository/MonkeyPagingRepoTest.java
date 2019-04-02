package com.github.fish56.springdatajpa.repository;

import com.alibaba.fastjson.JSONObject;
import com.github.fish56.springdatajpa.entity.Monkey;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MonkeyPagingRepoTest {
    @Autowired
    private MonkeyPagingRepo monkeyPagingRepo;

    @Test
    public void page(){
        Pageable pageable = PageRequest.of(1, 4);
        Page<Monkey> monkeyPage = monkeyPagingRepo.findAll(pageable);
        System.out.println(JSONObject.toJSONString(monkeyPage));
        for(Monkey monkey : monkeyPage.getContent()){
            System.out.println(JSONObject.toJSONString(monkey));
        }
    }
}