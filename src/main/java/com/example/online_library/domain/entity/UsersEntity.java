package com.example.online_library.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsersEntity {

    @Id
    @Column(name = "userid", nullable = false)
    @GeneratedValue(strategy = IDENTITY)
    private Long userID;

    @Column(name = "lastname", nullable = false)
    private String lastName;

    @Column(name = "firstname", nullable = false)
    private String firstName;

    private String password;

    private String email;

    private BigDecimal balance;

    @Column(name = "usertype", nullable = false)
    private Long userType;

    @Column(name = "bonuspoints", nullable = false)
    private Long bonusPoints;

    private String address;

    private String basket;

    private String phone;


}
