package com.example.orderService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.orderService.dto.OrderRequest;
import com.example.orderService.service.OrderService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @CircuitBreaker(name = "inventory", fallbackMethod = "fallbackBasketService")
    public String createOrderAPI(@RequestBody OrderRequest orderRequest) {
        return orderService.createOrder(orderRequest);
    }

    public String fallbackBasketService(OrderRequest orderRequest, Throwable t) {
        return "Fallback response:: Basket service is down. Please try again later";
    }
}
