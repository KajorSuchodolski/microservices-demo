package com.example.microservicesdemo.repository;

import com.example.microservicesdemo.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<Account, UUID> {
    Optional<Account> findAccountByLogin(String login);

    void deleteAccountByLogin(String login);
}
