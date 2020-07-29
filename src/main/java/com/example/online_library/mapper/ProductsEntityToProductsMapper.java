package com.example.online_library.mapper;

import com.example.online_library.domain.entity.ProductsEntity;
import com.example.online_library.domain.model.ProductsDTO;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ProductsEntityToProductsMapper implements Converter<ProductsEntity, ProductsDTO> {
    @Override
    public ProductsDTO convert(ProductsEntity source) {
        return ProductsDTO
                .builder()
                .productID(source.getProductID())
                .name(source.getName())
                .category(source.getCategory())
                .quantity(source.getQuantity())
                .description(source.getDescription())
                .price(source.getPrice())
                .shippingDays(source.getShippingDays())
                .rating(source.getRating())
                .totalrating(source.getTotalrating())
                .build();



    }
}
