package com.example.online_library.mapper;

import com.example.online_library.domain.entity.UsersEntity;
import com.example.online_library.domain.model.UsersDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import org.springframework.core.convert.converter.Converter;

@Component
@AllArgsConstructor
public class UsersToUsersEntityMapper implements Converter<UsersDTO, UsersEntity> {
    @Override
    public UsersEntity convert(UsersDTO source) {
        return UsersEntity.builder()
                .userID(source.getUserID())
                .lastName(source.getLastName())
                .firstName(source.getFirstName())
                .password(source.getPassword())
                .email(source.getEmail())
                .balance(source.getBalance())
                .userType(source.getUserType())
                .bonusPoints(source.getBonusPoints())
                .address(source.getAddress())
                .basket(source.getBasket())
                .phone(source.getPhone())
                .build();
    }
}

