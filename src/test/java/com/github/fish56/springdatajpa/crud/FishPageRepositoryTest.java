package com.github.fish56.springdatajpa.crud;

import com.alibaba.fastjson.JSONObject;
import com.github.fish56.springdatajpa.SpringDataJpaImplApplicationTests;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

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

    /**
     * 分页查询数据
     */
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

        System.out.println(JSONObject.toJSONString(fishPage.iterator()));

        for (Fish fish : fishPage){
            System.out.println(JSONObject.toJSONString(fish));
        }
//        {"id":7,"name":"fish 7"}
//        {"id":8,"name":"fish 8"}
    }

    @Test
    public void findById(){
        Pageable pageable = PageRequest.of(2, 2);
        List<Fish> fishList = fishPageRepository.findByIdGreaterThan(2L, pageable);
        System.out.println(JSONObject.toJSONString(fishList));
        // [{"id":7,"name":"fish 7"},{"id":8,"name":"fish 8"}]
    }
}