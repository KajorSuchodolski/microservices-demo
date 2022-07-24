package com.example.orderservice.service;

import com.example.orderservice.dto.AccountDto;
import com.example.orderservice.entity.Order;
import com.example.orderservice.repository.OrderRepository;
import com.example.orderservice.util.AccountFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.UUID;

@Service
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final AccountFeignClient accountFeignClient;

    @Autowired
    public OrderService(OrderRepository orderRepository, AccountFeignClient accountFeignClient) {
        this.orderRepository = orderRepository;
        this.accountFeignClient = accountFeignClient;
    }

    public Page<Order> getOrders(Pageable pageable) {
        return orderRepository.findAll(pageable);
    }

    public Order getOrderById(UUID id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Order with given id: " + id + " not found"));
    }

    public Order createOrder(String login, String token) {
        AccountDto accountDto = accountFeignClient.getAccount(login, token);
        if (accountDto == null) {
            throw new IllegalArgumentException("Account with given login: " + login + " not found");
        }
        Order order = new Order();
        order.setId(UUID.randomUUID());
        order.setClientId(accountDto.getId());
        order.setLogin(login);
        order.setCreatedAt(Instant.now());
        return orderRepository.save(order);
    }

    public void deleteOrder(UUID id) {
        orderRepository.deleteById(id);
    }
}
