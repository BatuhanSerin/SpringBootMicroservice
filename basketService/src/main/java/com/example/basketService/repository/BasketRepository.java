package com.example.basketService.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.basketService.model.Basket;

public interface BasketRepository extends JpaRepository<Basket, Long>{

    Optional<Basket> findByProductCode(String productCode);
    
}
