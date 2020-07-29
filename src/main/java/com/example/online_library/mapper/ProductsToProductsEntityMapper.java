package com.example.online_library.mapper;

import com.example.online_library.domain.entity.ProductsEntity;
import com.example.online_library.domain.model.ProductsDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import org.springframework.core.convert.converter.Converter;

@Component
@AllArgsConstructor
public class ProductsToProductsEntityMapper implements Converter<ProductsDTO, ProductsEntity> {
    @Override
    public ProductsEntity convert(ProductsDTO source) {
        return ProductsEntity.builder()
                .productID(source.getProductID())
                .name(source.getName())
                .quantity(source.getQuantity())
                .category(source.getCategory())
                .description(source.getDescription())
                .price(source.getPrice())
                .shippingDays(source.getShippingDays())
                .rating(source.getRating())
                .totalrating(source.getTotalrating())
                .build();
    }

}
