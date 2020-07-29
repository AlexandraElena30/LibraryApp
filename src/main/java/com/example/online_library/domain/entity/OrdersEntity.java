package com.example.online_library.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "orders")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrdersEntity {

    @Id
    @Column(name = "idcode",nullable = false)
    @GeneratedValue(strategy = IDENTITY)
    private Long idCode;

    private Long userID;

    private BigDecimal price;

    @Column(name = "orderdate",nullable = false)
    private LocalDate orderDate;

    @Column(name = "shippingdate",nullable = false)
    private LocalDate shippingDate;

    @Column(name = "orderproducts",nullable = false)
    private String orderProducts;

    private String status;
}
