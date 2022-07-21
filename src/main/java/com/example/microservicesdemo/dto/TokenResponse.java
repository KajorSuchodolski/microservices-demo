package com.example.microservicesdemo.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class TokenResponse {

    private String jwt;
}
