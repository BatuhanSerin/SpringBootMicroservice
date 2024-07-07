package com.example.basketService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.basketService.dto.basketResponse;
import com.example.basketService.service.BasketService;

@RestController
@RequestMapping("/basket")
public class BasketController {

    @Autowired
    private BasketService basketService;

    @GetMapping("/check")
    @ResponseBody
    // public boolean checkStock(@PathVariable("productCode") String productCode) {
    public List<basketResponse> checkStock(@RequestParam List<String> productCode) {
        return basketService.checkStock(productCode);
      
    }

}
