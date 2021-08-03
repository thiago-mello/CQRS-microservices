package com.test.productsmicroservice.commands;

import com.test.events.ProductCreatedEvent;
import com.test.productsmicroservice.entities.ProductLookupEntity;
import com.test.productsmicroservice.repositories.ProductLookupRepository;

import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@ProcessingGroup("product-group")
public class ProductLookupEventsHandler {
  private ProductLookupRepository productLookupRepository;

  @Autowired
  public ProductLookupEventsHandler(ProductLookupRepository productLookupRepository) {
    this.productLookupRepository = productLookupRepository;
  }

  @EventHandler
  public void on(ProductCreatedEvent event) {
    ProductLookupEntity product = new ProductLookupEntity(event.getProductId(), event.getTitle());
    this.productLookupRepository.save(product);
  }

}
