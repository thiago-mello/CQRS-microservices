package com.test.productsmicroservice.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Table(name = "products")
@Entity
public class ProductEntity implements Serializable {

  @Id
  @Column(unique = true)
  private String productId;

  @Column(unique = true)
  private String title;
  private Double price;
  private Integer quantity;
}
