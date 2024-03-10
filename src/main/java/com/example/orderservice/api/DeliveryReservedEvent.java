package com.example.orderservice.api;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DeliveryReservedEvent {
    private String productGuid;
    private String orderGuid;
}
