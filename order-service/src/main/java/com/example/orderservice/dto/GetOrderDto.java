package com.example.orderservice.dto;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
@Builder
public class GetOrderDto {

    private UUID id;
    private Instant createdAt;
    private UUID clientId;
    private String login;

}
