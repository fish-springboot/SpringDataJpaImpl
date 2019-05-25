package com.github.fish56.springdatajpa.one2many;

import com.alibaba.fastjson.JSONObject;
import com.github.fish56.springdatajpa.SpringDataJpaImplApplicationTests;
import com.github.fish56.springdatajpa.one2many.Article;
import com.github.fish56.springdatajpa.one2many.ArticleRepository;
import com.github.fish56.springdatajpa.one2many.Comment;
import com.github.fish56.springdatajpa.one2many.CommentRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ArticleRepositoryTest extends SpringDataJpaImplApplicationTests {
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private CommentRepository commentRepository;

    private Article article;
    private Comment comment1;
    private Comment comment2;
    private List<Comment> comments;

    /**
     * 初始化一个article以及连个comment供后期使用
     */
    @Before
    public void init(){
        article = new Article();
        article.setContent("这个一篇文章");

        comment1 = new Comment();
        comment1.setContent("还行");

        comment2 = new Comment();
        comment2.setContent("不错");

        comments = new ArrayList<>();
        comments.add(comment1);
        comments.add(comment2);
    }

    /**
     * 插入评论
     */
    @Test
    public void saveComment(){
        commentRepository.save(comment1);
        Iterable<Comment> comments = commentRepository.findAll();
        System.out.println(JSONObject.toJSONString(comments));
    }

    /**
     * 插入文章信息
     * 文章的评论内容也会被插入进去
     */
    @Test
    public void saveArticle(){

        article.setComments(comments);
        articleRepository.save(article);

        System.out.println(JSONObject.toJSONString(article));
        // {"comments":[{"content":"还行","id":2},{"content":"不错","id":3}],"content":"这个一篇文章","id":1}
    }

    /**
     * 查询所有评论
     */
    @Test
    public void getComments(){
        article.setComments(comments);
        articleRepository.save(article);

        Iterable<Comment> comments = commentRepository.findAll();
        System.out.println(JSONObject.toJSONString(comments));
        // [{"content":"还行","id":2},{"content":"不错","id":3}]
    }

    /**
     *
     */
    @Test
    public void getArticle(){
        article.setComments(comments);
        articleRepository.save(article);
        System.out.println(JSONObject.toJSONString(article));
        // {"comments":[{"content":"还行","id":2},{"content":"不错","id":3}],"content":"这个一篇文章","id":1}

        Optional<Article> articleOptional = articleRepository.findById(1L);
        System.out.println(JSONObject.toJSONString(articleOptional.get()));
        // {"comments":[],"content":"这个一篇文章","id":1}

        System.out.println(JSONObject.toJSONString(articleOptional.get().getComments()));
    }
}