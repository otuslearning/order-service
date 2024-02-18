package com.example.orderservice.exception;

public class OrderDuplicateCreateException extends RuntimeException {
    public static final String ORDER_DUPLICATE_CREATE = "Order already created";

    public OrderDuplicateCreateException() {
        super(ORDER_DUPLICATE_CREATE);
    }
    public OrderDuplicateCreateException(String message) {
        super(message);
    }
}
