package com.example.microservicesdemo.mapper;


import com.example.microservicesdemo.dto.CreateAccountDto;
import com.example.microservicesdemo.entity.Account;
import com.example.microservicesdemo.dto.GetAccountDto;

import java.util.List;
import java.util.UUID;

public class AccountMapper {

    public static GetAccountDto accountToGetAccountDto(Account account) {
        return GetAccountDto.builder()
                .id(account.getId())
                .login(account.getLogin())
                .firstName(account.getFirstName())
                .lastName(account.getLastName())
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

    public static List<GetAccountDto> accountsToGetAccountDtos(List<Account> accounts) {
        return accounts.stream()
                .map(AccountMapper::accountToGetAccountDto)
                .toList();
    }
}
