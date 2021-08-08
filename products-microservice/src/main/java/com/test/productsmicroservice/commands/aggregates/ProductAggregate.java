package com.test.productsmicroservice.commands.aggregates;

import com.test.events.ProductCreatedEvent;
import com.test.productsmicroservice.commands.CreateProductCommand;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

@Aggregate
public class ProductAggregate {

  @AggregateIdentifier
  private String productId;
  private String title;
  private Double price;
  private Integer quantity;

  public ProductAggregate() {
  }

  @CommandHandler
  public ProductAggregate(CreateProductCommand createProductCommand) throws Exception {

    ProductCreatedEvent productCreatedEvent = new ProductCreatedEvent();

    BeanUtils.copyProperties(createProductCommand, productCreatedEvent);

    AggregateLifecycle.apply(productCreatedEvent);

    throw new Exception("Hello test");
  }

  @EventSourcingHandler
  public void on(ProductCreatedEvent productCreatedEvent) {
    this.productId = productCreatedEvent.getProductId();
    this.price = productCreatedEvent.getPrice();
    this.quantity = productCreatedEvent.getQuantity();
    this.title = productCreatedEvent.getTitle();
  }

}
