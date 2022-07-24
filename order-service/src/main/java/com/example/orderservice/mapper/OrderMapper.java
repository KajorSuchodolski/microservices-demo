package com.example.orderservice.mapper;

import com.example.orderservice.dto.CreateOrderDto;
import com.example.orderservice.dto.GetOrderDto;
import com.example.orderservice.entity.Order;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class OrderMapper {
    public static GetOrderDto orderToGetOrderDto(Order order) {
        return GetOrderDto.builder()
                .id(order.getId())
                .createdAt(order.getCreatedAt())
                .clientId(order.getClientId())
                .login(order.getLogin())
                .build();
    }

    public static Order createOrderDtotoOrder(CreateOrderDto createOrderDto) {
        Order order = new Order();
        order.setId(UUID.randomUUID());
        order.setCreatedAt(Instant.now());
        order.setClientId(createOrderDto.getClientId());
        return order;
    }
}
