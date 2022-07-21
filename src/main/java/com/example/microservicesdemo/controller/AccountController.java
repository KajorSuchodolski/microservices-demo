package com.example.microservicesdemo.controller;

import com.example.microservicesdemo.entity.Account;
import com.example.microservicesdemo.mapper.AccountMapper;
import com.example.microservicesdemo.model.GetAccountDto;
import com.example.microservicesdemo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public List<GetAccountDto> getAllAccounts() {
        return AccountMapper.accountsToGetAccountDtos(accountService.getAllAccounts());
    }
}
