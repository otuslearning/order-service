package com.example.orderservice.api;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OrderDto {
    private Long orderId;
    private String productGuid;
    private String status;
    private Integer quantity;
}
