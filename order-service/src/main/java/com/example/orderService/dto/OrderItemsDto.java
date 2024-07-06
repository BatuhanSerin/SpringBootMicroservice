package com.example.orderService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemsDto {
    private Long id;
    private String productCode;
    private Double price;
    private Integer quantity;
}
