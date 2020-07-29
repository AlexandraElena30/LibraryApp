package com.example.online_library.mapper;

import com.example.online_library.domain.entity.PromotionEntity;
import com.example.online_library.domain.model.PromotionDTO;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PromotionEntityToPromotionMapper implements Converter<PromotionEntity, PromotionDTO> {
    @Override
    public PromotionDTO convert(PromotionEntity source) {
        return PromotionDTO.builder()
                .code(source.getCode())
                .promotionType(source.getPromotionType())
                .amount(source.getAmount())
                .build();
    }
}
