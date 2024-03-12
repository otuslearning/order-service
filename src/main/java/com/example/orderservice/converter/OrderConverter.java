package com.example.orderservice.converter;

import com.example.orderservice.api.CreateOrderRequestDto;
import com.example.orderservice.api.OrderCreateEvent;
import com.example.orderservice.api.OrderDto;
import com.example.orderservice.domain.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderConverter {
    public Order convert(CreateOrderRequestDto dto) {
        Order order = new Order();
        order.setProductGuid(dto.getProductGuid());
        order.setRequestGuid(dto.getRequestGuid());
        order.setQuantity(dto.getQuantity());
        order.setPrice(dto.getPrice());
        order.setDate(dto.getDate());
        return order;
    }

    public OrderCreateEvent convertToEvent(Order order) {
        return OrderCreateEvent.builder()
                .accountGuid(order.getAccountGuid())
                .orderGuid(order.getOrderGuid())
                .productGuid(order.getProductGuid())
                .quantity(order.getQuantity())
                .price(order.getPrice())
                .status(order.getStatus())
                .date(order.getDate())
                .build();
    }

    public OrderDto convert(Order order) {
        return OrderDto.builder()
                .orderGuid(order.getOrderGuid())
                .productGuid(order.getProductGuid())
                .quantity(order.getQuantity())
                .status(order.getStatus())
                .date(order.getDate())
                .build();
    }

    public List<OrderDto> convert(List<Order> orders) {
        return orders.stream()
                .map(this::convert)
                .toList();
    }
}
