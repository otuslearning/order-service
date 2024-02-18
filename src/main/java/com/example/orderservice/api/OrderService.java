package com.example.orderservice.api;


public interface OrderService {
    void createOrder(CreateOrderRequestDto createOrderRequestDto);
    ListOrderResponseDto getOrdersForOwner();
}
