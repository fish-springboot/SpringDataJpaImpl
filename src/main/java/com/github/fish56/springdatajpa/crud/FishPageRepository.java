package com.github.fish56.springdatajpa.crud;

import com.github.fish56.springdatajpa.crud.Fish;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * 实现了分页和排序的功能
 */
public interface FishPageRepository extends PagingAndSortingRepository<Fish, Long> {
    /**
     * 查询出id比某个值大的记录，并开启分页查询
     * @param id
     * @param page
     * @return
     */
    public List<Fish> findByIdGreaterThan(Long id, Pageable page);
}
