package com.github.fish56.springdatajpa.one2many;

import com.github.fish56.springdatajpa.one2many.Article;
import org.springframework.data.repository.CrudRepository;

public interface ArticleRepository extends CrudRepository<Article, Long> {
}
