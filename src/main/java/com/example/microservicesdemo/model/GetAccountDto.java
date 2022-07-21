package com.example.microservicesdemo.model;

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
