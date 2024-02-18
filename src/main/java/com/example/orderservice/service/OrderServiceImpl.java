package com.example.orderservice.service;

import com.example.orderservice.api.CreateOrderRequestDto;
import com.example.orderservice.api.ListOrderResponseDto;
import com.example.orderservice.api.OrderService;
import com.example.orderservice.api.RequestContextService;
import com.example.orderservice.converter.OrderConverter;
import com.example.orderservice.domain.Order;
import com.example.orderservice.exception.OrderDuplicateCreateException;
import com.example.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderConverter orderConverter;
    private final RequestContextService requestContextService;
    private final OrderRepository orderRepository;
    private final String ORDER_DUPLICATE_CREATE_REQUEST = "Order already created, requestGuid: %s";
    @Override
    public void createOrder(CreateOrderRequestDto createOrderRequestDto) {
        Order order = orderConverter.convert(createOrderRequestDto);
        order.setAccountGuid(requestContextService.getRequestContext().getAccountGuid());
        order.setStatus("CREATED");
        try {
            orderRepository.save(order);
        } catch (Exception e) {
            throw new OrderDuplicateCreateException(
                    String.format(ORDER_DUPLICATE_CREATE_REQUEST, createOrderRequestDto.getRequestGuid()));
        }
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
}
