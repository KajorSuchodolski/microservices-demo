package com.example.orderservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class AccountDto {

    @JsonProperty(value = "id")
    private UUID id;

    @JsonProperty(value = "login")
    private String login;
}
