package com.github.fish56.springdatajpa.repository;

import com.github.fish56.springdatajpa.entity.Monkey;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface MonkeyPagingRepo
        extends PagingAndSortingRepository<Monkey, Long> {
}
