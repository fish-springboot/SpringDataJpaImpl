package com.github.fish56.springdatajpa.repository;

import com.alibaba.fastjson.JSONObject;
import com.github.fish56.springdatajpa.SpringDataJpaImplApplicationTests;
import com.github.fish56.springdatajpa.entity.Panda;
import com.github.fish56.springdatajpa.entity.Wallet;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class WalletCrudRepositoryTest extends SpringDataJpaImplApplicationTests {
    @Autowired
    private WalletCrudRepository repository;

    private Wallet wallet1;
    private Wallet wallet2;
    private Panda panda1;
    private Panda panda2;

    @Before
    public void init(){
        panda1 = new Panda();
        panda1.setLastName("Snow");
        panda2 = new Panda();
        panda2.setLastName("Jon");

        wallet1 = new Wallet();
        wallet1.setMoney(22);
        wallet1.setOwner(panda1);

        wallet2 = new Wallet();
        wallet2.setMoney(22);
        wallet2.setOwner(panda2);
    }

    @Test(expected = Exception.class)
    public void save(){
        // 保存之前需要把panda保存到数据库
        Wallet wallet = repository.save(wallet1);
        System.out.println(JSONObject.toJSONString(wallet));
    }

    @Autowired
    private PandaRepository pandaRepository;
    @Test
    public void save2(){
        pandaRepository.save(panda1);
        Wallet wallet = repository.save(wallet1);
        System.out.println(JSONObject.toJSONString(wallet));
        // {"id":1,"money":22,"owner":{"id":1,"lastName":"Snow"}}
    }
}