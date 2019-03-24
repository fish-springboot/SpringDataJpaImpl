package com.github.fish56.springdatajpa.repository;

import com.github.fish56.springdatajpa.entity.Monkey;
import org.springframework.data.repository.CrudRepository;

public interface MonkeyRepo extends CrudRepository<Monkey, Long> {
}
