package com.test.productsmicroservice.query.models;

import lombok.Data;

@Data
public class ProductRestModel {
  private String productId;
  private String title;
  private Double price;
  private Integer quantity;
}
