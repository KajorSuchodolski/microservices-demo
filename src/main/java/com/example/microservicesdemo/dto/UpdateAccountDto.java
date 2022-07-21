package com.example.microservicesdemo.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.UUID;


public class UpdateAccountDto extends CreateAccountDto {

    @NotNull(message = "Id is required")
    @Getter
    @Setter
    private UUID id;

}
