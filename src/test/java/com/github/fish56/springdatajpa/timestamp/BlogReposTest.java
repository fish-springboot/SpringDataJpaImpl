package com.github.fish56.springdatajpa.timestamp;

import com.alibaba.fastjson.JSONObject;
import com.github.fish56.springdatajpa.SpringDataJpaImplApplicationTests;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class BlogReposTest extends SpringDataJpaImplApplicationTests {
    @Autowired
    private BlogRepos blogRepos;

    /**
     * 可以看到，自动的帮我们设置了时间
     */
    @Test
    public void test(){
        Blog blog = new Blog();
        blog.setTitle("SpringDataJpa入门实战");
        blog.setContent("稳");
        blogRepos.save(blog);
        System.out.println(JSONObject.toJSONString(blog));
        // {"content":"稳","createTime":1558766830592,"id":1,"title":"SpringDataJpa入门实战","updateTime":1558766830592}
    }
}