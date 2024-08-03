package com.example.basketService.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.basketService.dto.basketResponse;
import com.example.basketService.repository.BasketRepository;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class BasketService {
    @Autowired
    private final BasketRepository basketRepository;

    @Transactional(readOnly = true)
    @SneakyThrows
    public List<basketResponse> checkStock(List<String> productCode) {
        log.info("Checking stock for product codes: {}", productCode);
        Thread.sleep(10000);
        log.info("Checked stock for product codes: {}", productCode);
        return basketRepository.findByProductCodeIn(productCode).stream()
                .map(basket -> basketResponse.builder().productCode(basket.getProductCode())
                        .isInStock(basket.getQuantity() > 0).build())
                .toList();
    }

}
