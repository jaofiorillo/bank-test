package com.fiorillo.back_test.modules.account.repository;

import com.fiorillo.back_test.modules.account.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Integer>,
    QuerydslPredicateExecutor<Account> {

    Optional<Account> findByNumberAccount(Integer numberAccount);
}
