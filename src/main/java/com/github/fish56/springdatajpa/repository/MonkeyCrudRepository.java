package com.github.fish56.springdatajpa.repository;

import com.github.fish56.springdatajpa.entity.Monkey;
import org.springframework.data.repository.CrudRepository;

// CrudRepository预先定义了一些接口，可以前往源码出查看
public interface MonkeyCrudRepository extends CrudRepository<Monkey, Long> {
}
