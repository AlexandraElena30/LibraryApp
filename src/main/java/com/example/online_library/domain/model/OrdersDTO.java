package com.example.online_library.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrdersDTO {

    private Long idCode;

    private Long userID;

    private BigDecimal price;

    private LocalDate orderDate;

    private LocalDate shippingDate;

    private String orderProducts;

    private String status;
}
