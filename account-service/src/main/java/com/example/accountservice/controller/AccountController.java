package com.example.accountservice.controller;

import com.example.accountservice.dto.CreateAccountDto;
import com.example.accountservice.dto.UpdateAccountDto;
import com.example.accountservice.entity.Account;
import com.example.accountservice.mapper.AccountMapper;
import com.example.accountservice.dto.GetAccountDto;
import com.example.accountservice.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

    @PostMapping
    public ResponseEntity<GetAccountDto> createAccount(@RequestBody @Valid CreateAccountDto createAccountDto) {
        Account account = AccountMapper.createAccountDtoToAccount(createAccountDto);
        Account createdAccount = accountService.createAccount(account);
        GetAccountDto createdGetAccountDto = AccountMapper.accountToGetAccountDto(createdAccount);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdGetAccountDto);
    }

    @PutMapping("/{login}")
    public ResponseEntity<GetAccountDto> updateAccount(@PathVariable(name = "login") String login,
                                                       @RequestBody @Valid UpdateAccountDto updateAccountDto) {
        Account account = AccountMapper.updateAccountDtoToAccount(updateAccountDto);
        Account updatedAccount = accountService.updateAccount(account, login);
        GetAccountDto updatedGetAccountDto = AccountMapper.accountToGetAccountDto(updatedAccount);
        return ResponseEntity.ok(updatedGetAccountDto);
    }

    @DeleteMapping("/{login}")
    public ResponseEntity<Void> deleteAccount(@PathVariable(name = "login") String login) {
        accountService.deleteAccount(login);
        return ResponseEntity.noContent().build();
    }

}
