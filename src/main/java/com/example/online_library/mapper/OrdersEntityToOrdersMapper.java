package com.example.online_library.mapper;

import com.example.online_library.domain.entity.OrdersEntity;
import com.example.online_library.domain.model.OrdersDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.core.convert.converter.Converter;

@Component
@AllArgsConstructor
public class OrdersEntityToOrdersMapper implements Converter<OrdersEntity, OrdersDTO> {
    @Override
    public OrdersDTO convert(OrdersEntity source) {
        return OrdersDTO.builder()
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
