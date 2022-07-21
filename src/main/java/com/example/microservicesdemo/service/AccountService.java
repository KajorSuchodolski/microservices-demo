package com.example.microservicesdemo.service;

import com.example.microservicesdemo.entity.Account;
import com.example.microservicesdemo.repository.AccountRepository;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;
import java.util.UUID;

@Service
@Transactional
public class AccountService {

    private final AccountRepository accountRepository;
    private final Faker faker;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public AccountService(AccountRepository accountRepository, Faker faker, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.accountRepository = accountRepository;
        this.faker = faker;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PostConstruct
    public void init() {
        for (int i = 0; i < 50; i++) {
            Account account = new Account();
            account.setId(UUID.randomUUID());
            account.setLogin(faker.name().username());
            account.setPassword(bCryptPasswordEncoder.encode("Haslo123"));
            account.setFirstName(faker.name().firstName());
            account.setLastName(faker.name().lastName());

            if (i % 4 == 0) account.setRoles("ADMINISTRATOR");

            accountRepository.save(account);
        }
    }

    public Page<Account> getAllAccounts(Pageable pageable) {
        return accountRepository.findAll(pageable);
    }

    public Account getAccountByLogin(String login) {
        return accountRepository.findAccountByLogin(login)
                .orElseThrow(() -> new NotFoundException("Account by given login: " + login + " has not been found"));
    }

    public Account getAccountById(UUID id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Account by given id: " + id + " has not been found"));
    }

    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    public Account updateAccount(Account account, String login) {
        Account updatedAccount = accountRepository.findAccountByLogin(login)
                .orElseThrow(() -> new NotFoundException("Account by given login: " + login + " has not been found"));

        updatedAccount.setFirstName(account.getFirstName());
        updatedAccount.setLastName(account.getLastName());
        return accountRepository.save(updatedAccount);
    }

    public void deleteAccount(String login) {
        accountRepository.deleteAccountByLogin(login);
    }
}
