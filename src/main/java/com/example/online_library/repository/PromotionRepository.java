package com.example.online_library.repository;

import com.example.online_library.domain.entity.PromotionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PromotionRepository extends JpaRepository<PromotionEntity,String> {

    @Query("select s from PromotionEntity s ")
    List<PromotionEntity> getAll();

    Long deleteByCode(String code);
}
