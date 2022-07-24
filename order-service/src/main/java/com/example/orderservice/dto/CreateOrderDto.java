package com.example.orderservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@NoArgsConstructor
public class CreateOrderDto {

    @NotNull(message = "Client id must be specified")
    @JsonProperty(value = "clientId")
    private UUID clientId;

}
