package com.example.online_library.mapper;

import com.example.online_library.domain.entity.OrdersEntity;
import com.example.online_library.domain.model.OrdersDTO;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class OrdersToOrdersEntityMapper implements Converter<OrdersDTO, OrdersEntity> {
    @Override
    public OrdersEntity convert(OrdersDTO source) {
        return OrdersEntity.builder()
                .idCode(source.getIdCode())
                .userID(source.getUserID())
                .price(source.getPrice())
                .orderDate(source.getOrderDate())
                .shippingDate(source.getShippingDate())
                .orderProducts(source.getOrderProducts())
                .status(source.getStatus())
                .build();

    }
}

