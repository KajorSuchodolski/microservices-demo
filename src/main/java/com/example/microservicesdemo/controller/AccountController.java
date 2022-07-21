package com.example.microservicesdemo.controller;

import com.example.microservicesdemo.dto.CreateAccountDto;
import com.example.microservicesdemo.dto.UpdateAccountDto;
import com.example.microservicesdemo.entity.Account;
import com.example.microservicesdemo.mapper.AccountMapper;
import com.example.microservicesdemo.dto.GetAccountDto;
import com.example.microservicesdemo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public ResponseEntity<Page<GetAccountDto>> getAllAccounts(@RequestParam(required = false, defaultValue = "1") int page,
                                         @RequestParam(required = false, defaultValue = "10") int size,
                                         @RequestParam(required = false, defaultValue = "firstName") String sortBy) {

        if(!sortBy.equals("firstName") && !sortBy.equals("lastName") && !sortBy.equals("login")) {
            throw new IllegalArgumentException("Sort by must be one of: firstName, lastName, login");
        }

        page = page < 1 ? 1 : page;
        size = size < 1 ? 10 : size;

        Pageable pageable = PageRequest.of(page - 1, size, Sort.by(sortBy));
        Page<GetAccountDto> accounts = accountService.getAllAccounts(pageable)
                .map(AccountMapper::accountToGetAccountDto);

        return ResponseEntity.ok(accounts);
    }

    @GetMapping("/{login}")
    public ResponseEntity<GetAccountDto> getAccountByLogin(@PathVariable(name = "login") String login) {
        Account account = accountService.getAccountByLogin(login);
        GetAccountDto getAccountDto = AccountMapper.accountToGetAccountDto(account);
        return ResponseEntity.ok(getAccountDto);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<GetAccountDto> getAccountById(@PathVariable(name = "id") UUID id) {
        Account account = accountService.getAccountById(id);
        GetAccountDto getAccountDto = AccountMapper.accountToGetAccountDto(account);
        return ResponseEntity.ok(getAccountDto);
    }

    @PostMapping
    public ResponseEntity<GetAccountDto> createAccount(@RequestBody @Valid CreateAccountDto createAccountDto) {
        Account account = AccountMapper.createAccountDtoToAccount(createAccountDto);
        Account createdAccount = accountService.createAccount(account);
        GetAccountDto createdGetAccountDto = AccountMapper.accountToGetAccountDto(createdAccount);
        return ResponseEntity.created(null).body(createdGetAccountDto);
    }

    @PutMapping
    public ResponseEntity<GetAccountDto> updateAccount(@RequestBody @Valid UpdateAccountDto updateAccountDto) {
        Account account = AccountMapper.updateAccountDtoToAccount(updateAccountDto);
        Account updatedAccount = accountService.updateAccount(account);
        GetAccountDto updatedGetAccountDto = AccountMapper.accountToGetAccountDto(updatedAccount);
        return ResponseEntity.ok(updatedGetAccountDto);
    }

}
