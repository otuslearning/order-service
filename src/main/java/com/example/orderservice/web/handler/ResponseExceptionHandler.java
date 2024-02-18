package com.example.orderservice.web.handler;

import com.example.orderservice.exception.OrderDuplicateCreateException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(OrderDuplicateCreateException.class)
    @ResponseStatus(HttpStatus.CREATED)
    public void handleDuplicateCreateException(OrderDuplicateCreateException exception) {
       log.info(exception.getMessage());
    }
}
