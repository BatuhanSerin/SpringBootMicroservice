package com.example.basketService.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.basketService.dto.basketResponse;
import com.example.basketService.repository.BasketRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class BasketService {
    @Autowired
    private final BasketRepository basketRepository;

    @Transactional(readOnly = true)
    public List<basketResponse> checkStock(List<String> productCode) {
        return basketRepository.findByProductCodeIn(productCode).stream()
        .map(basket ->
            basketResponse.builder().productCode(basket.getProductCode())
            .isInStock(basket.getQuantity()>0).build()
        ).toList();
    }

}
