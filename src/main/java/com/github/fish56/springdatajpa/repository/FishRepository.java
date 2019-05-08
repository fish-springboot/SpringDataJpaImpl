package com.github.fish56.springdatajpa.repository;

import com.github.fish56.springdatajpa.entity.Fish;
import org.springframework.data.repository.CrudRepository;

public interface FishRepository extends CrudRepository<Fish, Long> {
}
