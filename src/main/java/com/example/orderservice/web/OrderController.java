package com.example.orderservice.web;

import com.example.orderservice.api.CreateOrderRequestDto;
import com.example.orderservice.api.ListOrderResponseDto;
import com.example.orderservice.api.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("${application.web.prefix.public}")
public class OrderController {
    private final OrderService orderService;
    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public void createOrder(@RequestBody CreateOrderRequestDto dto) {
        orderService.createOrder(dto);
    }

    @GetMapping("/{orderGuid}")
    public void createOrder(@PathVariable("orderGuid") String orderGuid) {
        orderService.getOrderByGuid(orderGuid);
    }

    @GetMapping
    public ListOrderResponseDto getOrdersForOwner() {
        return orderService.getOrdersForOwner();
    }
}
