package com.test.productsmicroservice.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "product_lookup")
public class ProductLookupEntity {

  public ProductLookupEntity() {
  }

  public ProductLookupEntity(String id, String title) {
    this.id = id;
    this.title = title;
  }

  @Id
  String id;

  @Column(unique = true)
  String title;
}
