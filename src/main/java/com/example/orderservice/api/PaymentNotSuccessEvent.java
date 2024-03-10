package com.example.orderservice.api;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PaymentNotSuccessEvent {
    private String orderGuid;
}
