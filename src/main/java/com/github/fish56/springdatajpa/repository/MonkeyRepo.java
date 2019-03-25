package com.github.fish56.springdatajpa.repository;

import com.github.fish56.springdatajpa.entity.Monkey;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MonkeyRepo extends CrudRepository<Monkey, Long> {
    List<Monkey> findMonkeyByIdGreaterThan(Long id);
}
