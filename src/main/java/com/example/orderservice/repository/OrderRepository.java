package com.example.orderservice.repository;

import com.example.orderservice.domain.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {
    List<Order> findByAccountGuid(String accountGuid);
}
