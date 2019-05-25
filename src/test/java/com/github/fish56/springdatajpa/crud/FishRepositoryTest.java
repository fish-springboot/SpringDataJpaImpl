package com.github.fish56.springdatajpa.crud;

import com.alibaba.fastjson.JSONObject;
import com.github.fish56.springdatajpa.SpringDataJpaImplApplicationTests;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

public class FishRepositoryTest extends SpringDataJpaImplApplicationTests {
    @Autowired
    private FishRepository fishRepository;

    /**
     * 当我们试图save重复数据的时候，
     * 第二次插入其实是更新数据
     */
    @Test
    public void saveUnique(){
        Fish fish = new Fish();
        fish.setName("Jon");

        Fish fish1 = fishRepository.save(fish);
        System.out.println(JSONObject.toJSONString(fish1));

        fish.setName("Jack");
        Fish fish2 = fishRepository.save(fish);
        System.out.println(JSONObject.toJSONString(fish));

//        Hibernate: insert into fish (id, name) values (null, ?)
//        {"id":1,"name":"Jon"}
//        Hibernate: select fish0_.id as id1_2_0_, fish0_.name as name2_2_0_ from fish fish0_ where fish0_.id=?
//        Hibernate: update fish set name=? where id=?
//        {"id":1,"name":"Jack"}

        /**
         * 调用save方法的时候，Jpa会首先前往数据查询一下id为1的用户是否存在
         *   如果不存在，就直接使用insert语句
         *   如果存在，就使用update语句
         */
    }

    /**
     * name属性的nullable为false
     * 所以下面的语句会抛出异常
     */
    @Test(expected = DataIntegrityViolationException.class)
    public void saveNull(){
        Fish fish = new Fish();
        fishRepository.save(fish);
        System.out.println(JSONObject.toJSONString(fish));
    }
}