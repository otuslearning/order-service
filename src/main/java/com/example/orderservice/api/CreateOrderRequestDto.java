package com.example.orderservice.api;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateOrderRequestDto {
    private String requestGuid;
    private String productGuid;
    private Integer quantity;
    private String date;
}
