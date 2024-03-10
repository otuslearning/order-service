package com.example.orderservice.producer;

import com.example.orderservice.api.OrderCreateEvent;
import com.example.orderservice.exception.ConvertException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderCreateProducer {
    private final ObjectMapper mapper;
    private final KafkaTemplate<String, String> kafkaTemplate;
    public void sendMessage(OrderCreateEvent message){
        Assert.notNull(message, "message mustn't be null");
        try {
            kafkaTemplate.send("order-created", mapper.writeValueAsString(message));
        } catch (JsonProcessingException e) {
            log.error("Error convert and send order create event: {}", message, e);
            throw new ConvertException(e.getMessage());
        }
    }
}
