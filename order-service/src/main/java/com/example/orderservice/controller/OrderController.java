package com.example.orderservice.controller;

import com.example.orderservice.dto.GetOrderDto;
import com.example.orderservice.entity.Order;
import com.example.orderservice.mapper.OrderMapper;
import com.example.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<Page<GetOrderDto>> getOrders(@RequestParam(value = "page", defaultValue = "1") int page,
                                                       @RequestParam(value = "size", defaultValue = "3") int size) {

        page = page < 1 ? 1 : page;
        size = size < 1 ? 1 : size;

        Pageable pageable = PageRequest.of(page - 1, size);
        Page<GetOrderDto> orders = orderService.getOrders(pageable).map(OrderMapper::orderToGetOrderDto);

        return ResponseEntity.ok().body(orders);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetOrderDto> getOrder(@PathVariable UUID id) {
        Order order = orderService.getOrderById(id);
        GetOrderDto orderDto = OrderMapper.orderToGetOrderDto(order);
        return ResponseEntity.ok().body(orderDto);
    }

    @PostMapping("/{login}")
    public ResponseEntity<GetOrderDto> createOrder(@PathVariable(value = "login") String login,
                                                   @RequestHeader(value = "Authorization") String token) {
        Order createdOrder = orderService.createOrder(login, token);
        GetOrderDto orderDto = OrderMapper.orderToGetOrderDto(createdOrder);
        return ResponseEntity.ok().body(orderDto);
    }
}


