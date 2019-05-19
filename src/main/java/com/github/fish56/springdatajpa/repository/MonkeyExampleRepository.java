package com.github.fish56.springdatajpa.repository;

import com.github.fish56.springdatajpa.entity.Monkey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MonkeyExampleRepository extends JpaRepository<Monkey, Long> {
}
