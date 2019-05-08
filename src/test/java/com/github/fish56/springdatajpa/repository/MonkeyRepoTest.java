package com.github.fish56.springdatajpa.repository;

import com.github.fish56.springdatajpa.entity.Monkey;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MonkeyRepoTest {
    @Autowired
    private MonkeyRepo monkeyRepo;

    @Test
    public void save(){
        // Monkey monkey = new Monkey("Jack");
        // monkeyRepo.save(monkey);

        List<Monkey> monkeys = new ArrayList<>();
        Monkey monkey1 = new Monkey();
        monkey1.setName("Bob");
        Monkey monkey2 = new Monkey();
        monkey2.setName("Peter");
        monkeys.add(monkey1);
        monkeys.add(monkey2);
        monkeyRepo.saveAll(monkeys);
    }


    @Test
    public void find(){
        Optional<Monkey> monkey1 = monkeyRepo.findById(3l);
        System.out.println(monkey1.map(Monkey::getId).get() + ": "
                + monkey1.map(Monkey::getName).get());

        Iterable<Monkey> monkeys = monkeyRepo.findAll();
        for(Monkey monkey : monkeys){
            System.out.println(monkey.getId() + ": " + monkey.getName());
        }
    }

    @Test
    public void update(){
        Monkey monkey = new Monkey();
        monkey.setName("Jon");
        monkey.setBirthDay(new Date());
        monkey.setId(1l);
        monkeyRepo.save(monkey);
    }

    @Test
    public void delete(){
        monkeyRepo.deleteById(2l);
    }
}