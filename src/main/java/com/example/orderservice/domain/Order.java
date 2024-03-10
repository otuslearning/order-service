package com.example.orderservice.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Entity
@Getter
@Setter
@Table(name="orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String orderGuid;
    private String productGuid;
    private String accountGuid;
    private String status;
    @Column(nullable = false, unique = true)
    private String requestGuid;
    private Integer quantity;
    private String date;
}
