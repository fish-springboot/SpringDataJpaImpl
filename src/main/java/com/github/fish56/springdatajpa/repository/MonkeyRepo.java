package com.github.fish56.springdatajpa.repository;

import com.github.fish56.springdatajpa.entity.Monkey;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MonkeyRepo extends CrudRepository<Monkey, Long> {
    List<Monkey> findMonkeyByIdGreaterThan(Long id);

    @Query("select m from Monkey m")
    public List<Monkey> findAllMonkeyByQuery();

    @Query("select m from Monkey m where m.name like ?1%")
    public List<Monkey> findAllMonkeyNameBeginWith(String beginString);
}
