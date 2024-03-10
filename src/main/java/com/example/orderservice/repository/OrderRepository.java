package com.example.orderservice.repository;

import com.example.orderservice.domain.Order;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Order> findByOrderGuid(String orderGuid);
    List<Order> findByAccountGuid(String accountGuid);
}
