package com.github.fish56.springdatajpa.crud;

import com.github.fish56.springdatajpa.crud.Fish;
import org.springframework.data.repository.CrudRepository;

public interface FishRepository extends CrudRepository<Fish, Long> {
}
