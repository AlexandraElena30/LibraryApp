package com.example.online_library.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "promotion")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PromotionEntity {

    @Id
    private String code;

    @Column(name = "promotiontype", nullable = false)
    private Short promotionType;

    private Integer amount;



}
