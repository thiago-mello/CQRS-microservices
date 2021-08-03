package com.test.productsmicroservice.repositories;

import com.test.productsmicroservice.entities.ProductLookupEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductLookupRepository extends JpaRepository<ProductLookupEntity, String> {
  public ProductLookupEntity findByIdOrTitle(String id, String title);
}