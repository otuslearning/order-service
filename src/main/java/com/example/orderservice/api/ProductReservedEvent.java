package com.example.orderservice.api;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProductReservedEvent {
    private String productGuid;
    private String orderGuid;
}
