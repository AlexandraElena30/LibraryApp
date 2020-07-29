package com.example.online_library.mapper;
import com.example.online_library.domain.entity.ProductsEntity;
import com.example.online_library.domain.entity.PromotionEntity;
import com.example.online_library.domain.model.ProductsDTO;
import com.example.online_library.domain.model.PromotionDTO;
import org.springframework.core.convert.converter.Converter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PromotionToPromotionEntityMapper implements Converter<PromotionDTO, PromotionEntity> {
    @Override
    public PromotionEntity convert(PromotionDTO source) {
        return PromotionEntity.builder()
                .code(source.getCode())
                .promotionType(source.getPromotionType())
                .amount(source.getAmount())
                .build();
    }
}
