package com.github.fish56.springdatajpa.one2on2;

import com.alibaba.fastjson.JSONObject;
import com.github.fish56.springdatajpa.SpringDataJpaImplApplicationTests;
import com.github.fish56.springdatajpa.one2one.Panda;
import com.github.fish56.springdatajpa.one2one.PandaRepository;
import com.github.fish56.springdatajpa.one2one.Wallet;
import com.github.fish56.springdatajpa.one2one.WalletCrudRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@Slf4j
public class WalletCrudRepositoryTest extends SpringDataJpaImplApplicationTests {
    @Autowired
    private PandaRepository pandaRepository;
    @Autowired
    private WalletCrudRepository walletRepository;

    private Wallet wallet1;
    private Wallet wallet2;
    private Panda panda1;
    private Panda panda2;

    @Before
    public void init(){
        panda1 = new Panda().setLastName("Snow");
        panda2 = new Panda().setLastName("Jon");

        wallet1 = new Wallet().setMoney(33);
        wallet2 = new Wallet().setMoney(22);
    }

    @Test(expected = Exception.class)
    public void save(){
        // 保存之前需要把panda保存到数据库
        Wallet wallet = walletRepository.save(wallet1);
        System.out.println(JSONObject.toJSONString(wallet));
    }


    @Test
    public void save2(){
        //  object references an unsaved transient instance - save the transient instance before flushing
        panda1.setWallet(wallet1);
        pandaRepository.save(panda1);

        // 从打印结果中，我们可以看到
        System.out.println(panda1.getWallet());

        Long walletId = wallet1.getId();

        pandaRepository.delete(panda1);

        Optional<Wallet> walletOptional2 = walletRepository.findById(walletId);

        if (walletOptional2.isPresent()){
            System.out.println(walletOptional2.get());
        } else {
            System.out.println("数据库中wallet1也被删除了");
        }

    }
}