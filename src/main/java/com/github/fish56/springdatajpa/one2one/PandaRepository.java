package com.github.fish56.springdatajpa.one2one;

import com.github.fish56.springdatajpa.one2one.Panda;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;


/**
 * 善用IDE的智能提醒
 */
public interface PandaRepository extends CrudRepository<Panda, Long> {
    public Panda findByFirstNameOrLastName(String firstName, String lastName);
    public List<Panda> findByBirthDayBefore(Date date);
}
