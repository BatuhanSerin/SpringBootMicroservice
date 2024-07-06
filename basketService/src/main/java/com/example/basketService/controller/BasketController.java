package com.example.basketService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.basketService.service.BasketService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/basket")
public class BasketController {

    @Autowired
    private BasketService basketService;

    @GetMapping("/check")
    @ResponseBody
    // public boolean checkStock(@PathVariable("productCode") String productCode) {
    public boolean checkStock(@RequestParam String productCode) {
        return basketService.checkStock(productCode);
      
    }

}
