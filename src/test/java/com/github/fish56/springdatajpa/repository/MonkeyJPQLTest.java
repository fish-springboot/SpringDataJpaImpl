package com.github.fish56.springdatajpa.repository;

import com.alibaba.fastjson.JSONObject;
import com.github.fish56.springdatajpa.SpringDataJpaImplApplicationTests;
import com.github.fish56.springdatajpa.entity.Monkey;
import com.github.fish56.springdatajpa.jpql.MonkeyJPQL;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class MonkeyJPQLTest extends SpringDataJpaImplApplicationTests {
    @Autowired
    private MonkeyJPQL monkeyJPQL;

    private Monkey monkey1;
    private Monkey monkey2;
    private List<Monkey> monkeys;

    @Before
    public void init(){
        monkey1 = new Monkey();
        monkey1.setName("Bob");
        monkey2 = new Monkey();
        monkey2.setName("Peter");
        monkeys = new ArrayList<>();
    }

    @Test
    public void find(){
        monkeyJPQL.save(monkey1);
        monkeyJPQL.save(monkey2);

        monkeys = monkeyJPQL.findAllMonkeyByQuery();
        System.out.println(JSONObject.toJSONString(monkeys));
        // [{"id":1,"name":"Bob"},{"id":2,"name":"Peter"}]

        monkeys = monkeyJPQL.findAllMonkeyNameBeginWith("B");
        System.out.println(JSONObject.toJSONString(monkeys));
        // [{"id":1,"name":"Bob"}]

        monkeys = monkeyJPQL.findMonkeyWithNativeSQL(1L);
        System.out.println(JSONObject.toJSONString(monkeys));
        // [{"id":2,"name":"Peter"}]
    }


    /**
     * 这里我们的JPQL语句写的有问题，执行delById的时候会抛出异常的
     *    Not supported for DML operations
     *
     *  参考：stackoverflow.com/questions/44022076
     */
    @Test(expected = org.hibernate.hql.internal.QueryExecutionRequestException.class)
    public void delete(){
        monkeyJPQL.save(monkey1);
        monkeyJPQL.save(monkey2);

        monkeys = monkeyJPQL.findAllMonkeyByQuery();
        System.out.println(JSONObject.toJSONString(monkeys));
        // [{"id":1,"name":"Bob"},{"id":2,"name":"Peter"}]

        monkeyJPQL.delById(1L);
    }

    /**
     * 这次我们修正了我们的JQPL语句，可以看到我们的删除语句确实执行成功了
     */
    @Test
    public void delete2(){
        // 先向数据库中插入两个记录，方便未来删除
        monkeyJPQL.save(monkey1);
        monkeyJPQL.save(monkey2);
        monkeys = monkeyJPQL.findAllMonkeyByQuery();
        System.out.println(JSONObject.toJSONString(monkeys));
        // [{"id":1,"name":"Bob"},{"id":2,"name":"Peter"}]

        // 执行删除语句
        monkeyJPQL.del_ById(1L);

        //  检查我们是否删除了数据
        monkeys = monkeyJPQL.findAllMonkeyByQuery();
        System.out.println(JSONObject.toJSONString(monkeys));
        // [{"id":2,"name":"Peter"}]
    }
}