package com.github.fish56.springdatajpa.one2many;

import com.github.fish56.springdatajpa.one2many.Comment;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Comment, Long> {
}
