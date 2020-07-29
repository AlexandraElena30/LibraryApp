package com.example.online_library.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductsDTO {


    private Long productID;

    private String name;

    private String category;

    private int quantity;

    private String description;

    private BigDecimal price;

    private int shippingDays;

    private BigDecimal rating;

    private Long totalrating;

    public String toString() {
        return this.productID + " " + this.name;
    }

}
