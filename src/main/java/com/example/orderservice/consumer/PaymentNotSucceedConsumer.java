package com.example.orderservice.consumer;

import com.example.orderservice.api.PaymentNotSuccessEvent;
import com.example.orderservice.api.OrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class PaymentNotSucceedConsumer {
    private final ObjectMapper mapper;
    private final OrderService orderService;
    @KafkaListener(groupId = "${spring.kafka.consumer.group-id}", topics = "${kafka.topics.payment-not-succeed}")
    public void consume(String message) {
        try {
            PaymentNotSuccessEvent paymentNotSuccessEvent = mapper.readValue(message, PaymentNotSuccessEvent.class);
            orderService.updateOrderStatus(paymentNotSuccessEvent.getOrderGuid(), "PAYMENT_NOT_SUCCESS");
        } catch (JsonProcessingException e) {
            log.error("Error parsing message: {}", message, e);
        }
    }
}
