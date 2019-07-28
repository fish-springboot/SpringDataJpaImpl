package com.github.fish56.springdatajpa.crud;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface FishSpecificationRepository<Fish, Long> extends JpaSpecificationExecutor<Fish> {
}
