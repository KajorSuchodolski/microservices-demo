package com.example.accountservice.mapper;


import com.example.accountservice.dto.CreateAccountDto;
import com.example.accountservice.dto.GetAccountDto;
import com.example.accountservice.dto.UpdateAccountDto;
import com.example.accountservice.entity.Account;

import java.util.UUID;

public class AccountMapper {

    private AccountMapper() {
    }

    public static GetAccountDto accountToGetAccountDto(Account account) {
        return GetAccountDto.builder()
                .id(account.getId())
                .login(account.getLogin())
                .firstName(account.getFirstName())
                .lastName(account.getLastName())
                .roles(account.getRoles())
                .build();
    }

    public static Account createAccountDtoToAccount(CreateAccountDto createAccountDto) {
        Account account = new Account();
        account.setId(UUID.randomUUID());
        account.setLogin(createAccountDto.getLogin());
        account.setPassword(createAccountDto.getPassword());
        account.setFirstName(createAccountDto.getFirstName());
        account.setLastName(createAccountDto.getLastName());
        return account;
    }

    public static Account updateAccountDtoToAccount(UpdateAccountDto updateAccountDto) {
        Account account = new Account();
        account.setFirstName(updateAccountDto.getFirstName());
        account.setLastName(updateAccountDto.getLastName());
        return account;
    }

}
