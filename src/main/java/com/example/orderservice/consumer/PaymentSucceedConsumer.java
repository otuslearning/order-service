package com.example.orderservice.consumer;

import com.example.orderservice.api.OrderPaymentSuccessEvent;
import com.example.orderservice.api.OrderService;
import com.example.orderservice.api.PaymentSuccessEvent;
import com.example.orderservice.producer.OrderPaymentSuccessProducer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@RequiredArgsConstructor
public class PaymentSucceedConsumer {
    private final ObjectMapper mapper;
    private final OrderService orderService;
    private final OrderPaymentSuccessProducer orderPaymentSuccessProducer;
    @Transactional
    @KafkaListener(groupId = "${spring.kafka.consumer.group-id}", topics = "${kafka.topics.payment-succeed}")
    public void consume(String message) {
        try {
            PaymentSuccessEvent paymentSuccessEvent = mapper.readValue(message, PaymentSuccessEvent.class);
            orderService.updateOrderStatus(paymentSuccessEvent.getOrderGuid(), "PAYMENT_SUCCESS");
            OrderPaymentSuccessEvent orderPaymentSuccessEvent = orderService.buildOrderPaymentSuccessEvent(paymentSuccessEvent.getOrderGuid());
            orderPaymentSuccessProducer.sendMessage(orderPaymentSuccessEvent);
        } catch (JsonProcessingException e) {
            log.error("Error parsing message: {}", message, e);
        }
    }
}
