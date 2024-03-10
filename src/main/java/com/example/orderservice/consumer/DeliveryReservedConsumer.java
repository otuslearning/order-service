package com.example.orderservice.consumer;

import com.example.orderservice.api.DeliveryReservedEvent;
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
public class DeliveryReservedConsumer {
    private final ObjectMapper mapper;
    private final OrderService orderService;
    @KafkaListener(groupId = "${spring.kafka.consumer.group-id}", topics = "${kafka.topics.delivery-reserved}")
    public void consume(String message) {
        try {
            DeliveryReservedEvent productNotReservedEvent = mapper.readValue(message, DeliveryReservedEvent.class);
            orderService.updateOrderStatus(productNotReservedEvent.getOrderGuid(), "DELIVERY_RESERVED");
        } catch (JsonProcessingException e) {
            log.error("Error parsing message: {}", message, e);
        }
    }
}
