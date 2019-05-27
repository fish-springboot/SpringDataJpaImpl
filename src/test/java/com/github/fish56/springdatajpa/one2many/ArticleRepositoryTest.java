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
     * 初始化一个article以及两个comment供后期使用
     */
    @Before
    public void init(){
        article = new Article().setContent("这个一篇文章");

        comment1 = new Comment().setContent("还行");
        comment2 = new Comment().setContent("不错");

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
        // [{"content":"还行","id":1}]
    }

    /**
     * 插入文章信息
     * 文章附带的评论内容也会被自动的插入进去
     */
    @Test
    public void saveArticle(){

        article.setComments(comments);
        articleRepository.save(article);

        System.out.println(JSONObject.toJSONString(article));
        // {"comments":[{"content":"还行","id":2},{"content":"不错","id":3}],"content":"这个一篇文章","id":1}

        /**
         * 从打印结果中，我们可以看到，文章和评论都有了id，说明确实插入到了数据库
         *
         * 上述的操作对应的sql语句为：
         *   Hibernate: insert into article (content, id) values (?, ?)
         *   Hibernate: insert into comment (article_id, content, id) values (?, ?, ?)
         *   Hibernate: insert into comment (article_id, content, id) values (?, ?, ?)
         */
    }

    /**
     * 查询所有评论
     */
    @Test
    public void getComments(){
        // 先向数据库中插入数据
        article.setComments(comments);
        articleRepository.save(article);

        // 然后查询我们的文章评论
        Iterable<Comment> comments = commentRepository.findAll();
        System.out.println(JSONObject.toJSONString(comments));
        // [{"content":"还行","id":2},{"content":"不错","id":3}]
    }

    /**
     * 通过这个测试用例我们可以发现：
     *   - 把article插入数据库的时候，它的comments也会被插入数据库
     *   - 查询article的时候，也会查询它的comments
     *
     * 这也是JPA便利的地方，相对与其他的框架，可以节省喝多样板代码
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
        // [{"content":"还行","id":2},{"content":"不错","id":3}]
    }
}