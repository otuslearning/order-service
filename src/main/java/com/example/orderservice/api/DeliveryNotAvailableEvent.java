package com.example.orderservice.api;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DeliveryNotAvailableEvent {
    private String date;
    private String orderGuid;
}
