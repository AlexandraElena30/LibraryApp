package com.example.online_library.service;

import com.example.online_library.domain.entity.PromotionEntity;
import com.example.online_library.domain.model.PromotionDTO;
import com.example.online_library.exception.PromotionNotFoundException;
import com.example.online_library.mapper.PromotionEntityToPromotionMapper;
import com.example.online_library.mapper.PromotionToPromotionEntityMapper;
import com.example.online_library.repository.PromotionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Validated
public class PromotionService {

    private final PromotionRepository repository;

    private final PromotionEntityToPromotionMapper promotionEntityToPromotionMapper;

    private final PromotionToPromotionEntityMapper promotionToPromotionEntityMapper;

    public PromotionDTO findById(String promotionId) {
        return repository.findById(promotionId)
                .map(promotionEntityToPromotionMapper::convert)
                .orElseThrow(() -> new PromotionNotFoundException("No promotion found"));
    }

    public List<PromotionDTO> getAll() {
        return repository.getAll()
                .stream()
                .map(promotionEntityToPromotionMapper::convert)
                .collect(Collectors.toList());
    }

    public void addPromotion(String code, Short type, int amount) {
        PromotionDTO promo = new PromotionDTO(code, type, amount);
        PromotionEntity promotionEntity = promotionToPromotionEntityMapper.convert(promo);
        repository.save(promotionEntity);
    }

    @Transactional
    public Long removePromotion(String code) {
        return repository.deleteByCode(code);
    }
}

