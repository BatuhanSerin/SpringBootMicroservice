package com.example.orderService.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.orderService.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{
    
}
