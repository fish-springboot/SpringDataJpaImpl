package com.github.fish56.springdatajpa.repository;

import com.github.fish56.springdatajpa.entity.Monkey;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MonkeyRepoQueryTest {
    @Autowired
    private MonkeyRepo monkeyRepo;

    @Test
    public void find(){
        List<Monkey> monkeys = monkeyRepo.findMonkeyByIdGreaterThan(1l);

        for(Monkey monkey : monkeys){
            System.out.println(monkey.getId() + ": " + monkey.getName());
        }
    }

    @Test
    public void findAllMonkeyByQuery(){
        List<Monkey> monkeys = monkeyRepo.findAllMonkeyByQuery();

        for(Monkey monkey : monkeys){
            System.out.println(monkey.getId() + ": " + monkey.getName());
        }
    }

    @Test
    public void findAllMonkeyNameBeginWith(){
        List<Monkey> monkeys = monkeyRepo
                       .findAllMonkeyNameBeginWith("Ja");

        for(Monkey monkey : monkeys){
            System.out.println(monkey.getId() + ": " + monkey.getName());
        }
    }
}