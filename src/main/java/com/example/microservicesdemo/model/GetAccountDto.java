package com.example.microservicesdemo.model;

import lombok.Data;
import java.util.UUID;

@Data
public class GetAccountDto {

    private UUID id;
    private String login;
    private String firstName;
    private String lastName;
}
