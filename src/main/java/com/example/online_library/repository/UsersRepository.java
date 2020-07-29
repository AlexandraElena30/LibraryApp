package com.example.online_library.repository;


import com.example.online_library.domain.entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<UsersEntity, Long> {

    @Query("select s from UsersEntity s ")
    List<UsersEntity> getAll();

    Optional<UsersEntity> findByEmail(String email);

}
