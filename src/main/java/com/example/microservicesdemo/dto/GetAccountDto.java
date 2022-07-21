package com.example.microservicesdemo.dto;

import lombok.Builder;
import lombok.Data;
import java.util.UUID;

@Data
@Builder
public class GetAccountDto {

    private UUID id;
    private String login;
    private String firstName;
    private String lastName;
}