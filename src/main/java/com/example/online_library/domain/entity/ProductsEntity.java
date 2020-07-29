package com.example.online_library.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "products")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductsEntity {

    @Id
    private Long productID;

    private String name;

    private String category;

    private int quantity;

    private String description;

    private BigDecimal price;

    @Column(name = "shippingdays",nullable = false)
    private int shippingDays;

    private BigDecimal rating;

    private Long totalrating;

}
