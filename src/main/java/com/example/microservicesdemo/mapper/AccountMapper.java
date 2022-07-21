package com.example.microservicesdemo.mapper;


import com.example.microservicesdemo.entity.Account;
import com.example.microservicesdemo.model.GetAccountDto;

import java.util.List;

public class AccountMapper {

    public static GetAccountDto accountToGetAccountDto(Account account) {
        return GetAccountDto.builder()
                .id(account.getId())
                .login(account.getLogin())
                .firstName(account.getFirstName())
                .lastName(account.getLastName())
                .build();
    }

    public static List<GetAccountDto> accountsToGetAccountDtos(List<Account> accounts) {
        return accounts.stream()
                .map(AccountMapper::accountToGetAccountDto)
                .toList();
    }
}
