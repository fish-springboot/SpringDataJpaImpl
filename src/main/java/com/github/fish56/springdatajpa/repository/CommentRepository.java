package com.github.fish56.springdatajpa.repository;

import com.github.fish56.springdatajpa.entity.Comment;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Comment, Long> {
}
