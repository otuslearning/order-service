package com.example.orderservice.api;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OrderDto {
    private String orderGuid;
    private String productGuid;
    private String status;
    private Integer quantity;
    private String date;
}
