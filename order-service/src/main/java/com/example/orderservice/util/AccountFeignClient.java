package com.example.orderservice.util;

import com.example.orderservice.dto.AccountDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "account-service")
public interface AccountFeignClient {

    @GetMapping(
            value = "/accounts/{login}",
            produces = "application/json"
    )
    AccountDto getAccount(@PathVariable(value = "login") String login,
                          @RequestHeader(value = "Authorization") String token);
}
