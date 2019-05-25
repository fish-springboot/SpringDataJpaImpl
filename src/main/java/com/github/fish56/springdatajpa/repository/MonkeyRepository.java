package com.github.fish56.springdatajpa.repository;

import com.github.fish56.springdatajpa.entity.Monkey;
import org.springframework.data.repository.Repository;

/**
 * 泛型是实体类型、主键类型
 */
public interface MonkeyRepository extends Repository<Monkey, Long> {
}
