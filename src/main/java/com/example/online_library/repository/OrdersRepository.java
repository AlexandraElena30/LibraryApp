package com.example.online_library.repository;


import com.example.online_library.domain.entity.OrdersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<OrdersEntity,Long> {

    @Query("select s from OrdersEntity s where userID = ?1" )
    List<OrdersEntity> getAllForUser(Long id);

}
