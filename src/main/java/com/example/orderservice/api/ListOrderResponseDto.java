package com.example.orderservice.api;

import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
public class ListOrderResponseDto {
    @Builder.Default
    private List<OrderDto> content = new ArrayList<>();
    @Builder.Default
    private Integer total = 0;
}
