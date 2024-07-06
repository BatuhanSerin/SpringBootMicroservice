package com.example.basketService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.basketService.repository.BasketRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class BasketService {
    @Autowired
    private final BasketRepository basketRepository;

    @Transactional(readOnly = true)
    public boolean checkStock(String productCode) {
        return basketRepository.findByProductCode(productCode).isPresent();
    }

}
