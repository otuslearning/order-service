package com.example.orderservice.api;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentSuccessEvent {
    private String orderGuid;
}
