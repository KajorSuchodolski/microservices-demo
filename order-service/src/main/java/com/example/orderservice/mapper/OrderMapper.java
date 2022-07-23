package com.example.orderservice.mapper;

import com.example.orderservice.dto.GetOrderDto;
import com.example.orderservice.entity.Order;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class OrderMapper {

    public static GetOrderDto orderToGetOrderDto(Order order) {
        return GetOrderDto.builder()
                .id(order.getId())
                .createdAt(order.getCreatedAt())
                .clientId(order.getId())
                .build();
    }
}
