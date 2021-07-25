package com.test.productsmicroservice.repositories;

import java.util.Optional;

import com.test.productsmicroservice.entities.ProductEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, String> {
  Optional<ProductEntity> findByProductIdOrTitle(String productId, String title);
}
