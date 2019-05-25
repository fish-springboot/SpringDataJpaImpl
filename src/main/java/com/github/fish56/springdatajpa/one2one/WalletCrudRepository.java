package com.github.fish56.springdatajpa.one2one;

import com.github.fish56.springdatajpa.one2one.Wallet;
import org.springframework.data.repository.CrudRepository;

public interface WalletCrudRepository extends CrudRepository<Wallet, Long> {
}
