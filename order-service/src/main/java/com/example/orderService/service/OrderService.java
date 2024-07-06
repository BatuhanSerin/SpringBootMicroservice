package com.example.orderService.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.orderService.dto.OrderItemsDto;
import com.example.orderService.dto.OrderRequest;
import com.example.orderService.model.Order;
import com.example.orderService.model.OrderItems;
import com.example.orderService.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;

    @Transactional
    public void createOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderItems> orderItems = orderRequest.getOrderItemsDtoList()
                .stream()
                .map(orderItemsDto -> mapToDto(orderItemsDto))
                .toList();

        order.setOrderItems(orderItems);

        orderRepository.save(order);

    }

    private OrderItems mapToDto(OrderItemsDto orderItemsDto) {
        OrderItems orderItems = new OrderItems();
        orderItems.setProductCode(orderItemsDto.getProductCode());
        orderItems.setPrice(orderItemsDto.getPrice());
        orderItems.setQuantity(orderItemsDto.getQuantity());
        return orderItems;
    }

}
