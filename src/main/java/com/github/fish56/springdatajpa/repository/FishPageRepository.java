package com.github.fish56.springdatajpa.repository;

import com.github.fish56.springdatajpa.entity.Fish;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * 实现了分页和排序的功能
 */
public interface FishPageRepository extends PagingAndSortingRepository<Fish, Long> {
}
