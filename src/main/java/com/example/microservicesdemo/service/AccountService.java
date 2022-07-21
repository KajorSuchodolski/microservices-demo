package com.example.microservicesdemo.service;

import com.example.microservicesdemo.entity.Account;
import com.example.microservicesdemo.repository.AccountRepository;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.UUID;

@Service
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
        for(int i = 0; i < 100; i++) {
            Account account = new Account();
            account.setId(UUID.randomUUID());
            account.setLogin(faker.name().username());
            account.setPassword(bCryptPasswordEncoder.encode("Haslo123"));
            account.setFirstName(faker.name().firstName());
            account.setLastName(faker.name().lastName());
            accountRepository.save(account);
        }
    }

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }
}