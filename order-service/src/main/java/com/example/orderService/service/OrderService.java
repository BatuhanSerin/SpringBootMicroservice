package com.example.orderService.service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.orderService.dto.OrderItemsDto;
import com.example.orderService.dto.OrderRequest;
import com.example.orderService.dto.basketResponse;
import com.example.orderService.event.OrderEvent;
import com.example.orderService.model.Order;
import com.example.orderService.model.OrderItems;
import com.example.orderService.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    @Value("${stockURI}")
    private String stockURI;

    private final WebClient.Builder webClientBuilder;
    private final OrderRepository orderRepository;
    private final KafkaTemplate<String, OrderEvent> kafkaTemplate;

    @Transactional
    public String createOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderItems> orderItems = orderRequest.getOrderItemsDtoList()
                .stream()
                .map(orderItemsDto -> mapToDto(orderItemsDto))
                .toList();

        order.setOrderItems(orderItems);

        List<String> productCodeList = order.getOrderItems().stream().map(orderItem -> orderItem.getProductCode())
                .toList();

        basketResponse[] basketResponses = webClientBuilder.build().get()
                .uri(stockURI,
                        uriBuilder -> uriBuilder.queryParam("productCode", productCodeList).build())
                .retrieve()
                .bodyToMono(basketResponse[].class)
                .block();

        boolean areAllProductsInStock = Arrays.stream(basketResponses)
                .allMatch(basketResponse -> basketResponse.isInStock());

        if (!areAllProductsInStock) {
            return ("Some products are not in stock");
        } else {
            orderRepository.save(order);
            kafkaTemplate.send("notification", order.getOrderNumber(), new OrderEvent(order.getOrderNumber()));
            return "Order Created Successfully";
        }

    }

    private OrderItems mapToDto(OrderItemsDto orderItemsDto) {
        OrderItems orderItems = new OrderItems();
        orderItems.setProductCode(orderItemsDto.getProductCode());
        orderItems.setPrice(orderItemsDto.getPrice());
        orderItems.setQuantity(orderItemsDto.getQuantity());
        return orderItems;
    }

}
