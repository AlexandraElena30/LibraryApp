package com.example.online_library.repository;

import com.example.online_library.domain.entity.BookProductsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookProductsRepository extends JpaRepository<BookProductsEntity,Long > {

    @Query("select s from BookProductsEntity s ")
    List<BookProductsEntity> getAll();

}
