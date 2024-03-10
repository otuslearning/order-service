package com.example.orderservice.consumer;

import com.example.orderservice.api.DeliveryNotAvailableEvent;
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
public class DeliveryNotReservedConsumer {
    private final ObjectMapper mapper;
    private final OrderService orderService;
    @KafkaListener(groupId = "${spring.kafka.consumer.group-id}", topics = "${kafka.topics.delivery-not-reserved}")
    public void consume(String message) {
        try {
            DeliveryNotAvailableEvent notReservedProductEvent = mapper.readValue(message, DeliveryNotAvailableEvent.class);
            orderService.updateOrderStatus(notReservedProductEvent.getOrderGuid(), "DELIVERY_NOT_RESERVED");
        } catch (JsonProcessingException e) {
            log.error("Error parsing message: {}", message, e);
        }
    }
}
