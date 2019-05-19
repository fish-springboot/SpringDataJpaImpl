package com.github.fish56.springdatajpa.repository;

import com.alibaba.fastjson.JSONObject;
import com.github.fish56.springdatajpa.SpringDataJpaImplApplicationTests;
import com.github.fish56.springdatajpa.entity.Fish;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import static org.junit.Assert.*;

public class FishPageRepositoryTest extends SpringDataJpaImplApplicationTests {
    @Autowired
    private FishPageRepository fishPageRepository;

    @Before
    public void page(){
        for (Long i = 0L; i < 10; i++) {
            Fish fish = new Fish();
            fish.setId(i);
            fish.setName("fish " + i.toString());
            fishPageRepository.save(fish);
        }
    }

    @Test
    public void findAll(){
        // 创建分页对象, 每页容量为2，获取第三页
        Pageable pageable = PageRequest.of(3, 2);

        /**
         * 分页查询，返回的是iterable对象
         */
        Page<Fish> fishPage = fishPageRepository.findAll(pageable);

        System.out.println(fishPage.getTotalElements());
        // 9

        System.out.println(fishPage.getTotalPages());
        // 5

        for (Fish fish : fishPage){
            System.out.println(JSONObject.toJSONString(fish));
        }
//        {"id":7,"name":"fish 7"}
//        {"id":8,"name":"fish 8"}
    }


}