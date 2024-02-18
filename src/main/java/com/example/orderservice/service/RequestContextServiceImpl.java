package com.example.orderservice.service;

import com.example.orderservice.api.RequestContextDto;
import com.example.orderservice.api.RequestContextService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.example.orderservice.util.HttpUtils.REQUEST_ATTRIBUTE_CONTEXT;

@Service
@RequiredArgsConstructor
public class RequestContextServiceImpl implements RequestContextService {
    private final HttpServletRequest request;

    @Override
    public RequestContextDto getRequestContext() {
        return (RequestContextDto) request.getAttribute(REQUEST_ATTRIBUTE_CONTEXT);
    }
}
