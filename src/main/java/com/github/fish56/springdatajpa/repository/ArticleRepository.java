package com.github.fish56.springdatajpa.repository;

import com.github.fish56.springdatajpa.entity.Article;
import org.springframework.data.repository.CrudRepository;

public interface ArticleRepository extends CrudRepository<Article, Long> {
}
