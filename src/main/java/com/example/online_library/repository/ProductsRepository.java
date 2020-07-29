package com.example.online_library.repository;

import com.example.online_library.domain.entity.ProductsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductsRepository extends JpaRepository<ProductsEntity,Long> {

    @Query("select s from ProductsEntity s ")
    List<ProductsEntity> getAll();

    List<ProductsEntity> findAll();

    @Query("select s from ProductsEntity s where s.productID IN :inlist")
    List<ProductsEntity> getAllInList(@Param("inlist") String inlist);

    List<ProductsEntity> findByproductIDIn(List<Long> productIDList);

    List<ProductsEntity> findByNameContains(String name);

    List<ProductsEntity> findAllByCategory(String category);

}
