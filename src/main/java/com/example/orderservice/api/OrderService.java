package com.example.orderservice.api;


public interface OrderService {
    void createOrder(CreateOrderRequestDto createOrderRequestDto);
    OrderDto getOrderByGuid(String orderGuid);
    ListOrderResponseDto getOrdersForOwner();
    OrderPaymentSuccessEvent buildOrderPaymentSuccessEvent(String orderGuid);
    void updateOrderStatus(String orderGuid, String status);
}
