package com.example.online_library.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsersDTO {

    private Long userID;

    private String lastName;

    private String firstName;

    private String password;

    private String email;

    private BigDecimal balance;

    private Long userType;

    private Long bonusPoints;

    private String address;

    private String basket;

    private String phone;


}
