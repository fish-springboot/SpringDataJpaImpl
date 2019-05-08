package com.github.fish56.springdatajpa.repository;

import com.github.fish56.springdatajpa.entity.Wallet;
import org.springframework.data.repository.CrudRepository;

public interface WalletCrudRepository extends CrudRepository<Wallet, Long> {
}
