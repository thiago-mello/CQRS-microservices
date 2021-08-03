package com.test.productsmicroservice.query;

import com.test.events.ProductCreatedEvent;
import com.test.productsmicroservice.entities.ProductEntity;
import com.test.productsmicroservice.repositories.ProductRepository;

import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@ProcessingGroup("product-group")
public class ProductProjection {
  private ProductRepository productRepositopry;

  @Autowired
  public ProductProjection(ProductRepository productRepository) {
    this.productRepositopry = productRepository;
  }

  @EventHandler
  public void on(ProductCreatedEvent event) {
    ProductEntity product = new ProductEntity();

    BeanUtils.copyProperties(event, product);

    productRepositopry.save(product);
  }
}
