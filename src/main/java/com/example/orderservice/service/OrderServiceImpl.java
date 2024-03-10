package com.example.orderservice.service;

import com.example.orderservice.api.CreateOrderRequestDto;
import com.example.orderservice.api.ListOrderResponseDto;
import com.example.orderservice.api.OrderDto;
import com.example.orderservice.api.OrderPaymentSuccessEvent;
import com.example.orderservice.api.OrderService;
import com.example.orderservice.api.RequestContextService;
import com.example.orderservice.converter.OrderConverter;
import com.example.orderservice.domain.Order;
import com.example.orderservice.exception.OrderDuplicateCreateException;
import com.example.orderservice.producer.OrderCreateProducer;
import com.example.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderServiceImpl implements OrderService {
    private static final Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);
    private final OrderConverter orderConverter;
    private final RequestContextService requestContextService;
    private final OrderRepository orderRepository;
    private final OrderCreateProducer orderCreateProducer;
    private final String ORDER_DUPLICATE_CREATE_REQUEST = "Order already created, requestGuid: %s";
    @Override
    @Transactional
    public void createOrder(CreateOrderRequestDto createOrderRequestDto) {
        Order order = orderConverter.convert(createOrderRequestDto);
        order.setOrderGuid(UUID.randomUUID().toString());
        order.setAccountGuid(requestContextService.getRequestContext().getAccountGuid());
        order.setStatus("CREATED");
        try {
            orderRepository.save(order);
            orderCreateProducer.sendMessage(orderConverter.convertToEvent(order));
        } catch (Exception e) {
            throw new OrderDuplicateCreateException(
                    String.format(ORDER_DUPLICATE_CREATE_REQUEST, createOrderRequestDto.getRequestGuid()));
        }
    }

    @Override
    public OrderDto getOrderByGuid(String orderGuid) {
        Order order = orderRepository.findByOrderGuid(orderGuid).orElseThrow();
        return orderConverter.convert(order);
    }

    @Override
    public ListOrderResponseDto getOrdersForOwner() {
        List<Order> orders = orderRepository.findByAccountGuid(
                requestContextService.getRequestContext().getAccountGuid());
        return ListOrderResponseDto.builder()
                .content(orderConverter.convert(orders))
                .total(orders.size())
                .build();
    }

    @Override
    public OrderPaymentSuccessEvent buildOrderPaymentSuccessEvent(String orderGuid) {
        Order order = orderRepository.findByOrderGuid(orderGuid).orElseThrow();
        return OrderPaymentSuccessEvent.builder()
                .orderGuid(orderGuid)
                .productGuid(order.getProductGuid())
                .date(order.getDate())
                .quantity(order.getQuantity())
                .build();
    }

    @Override
    @Transactional
    public void updateOrderStatus(String orderGuid, String status) {
        Order order = orderRepository.findByOrderGuid(orderGuid).orElseThrow();
        order.setStatus(status);
        orderRepository.save(order);
    }
}
