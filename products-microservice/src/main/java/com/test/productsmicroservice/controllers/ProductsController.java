package com.test.productsmicroservice.controllers;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import javax.validation.Valid;

import com.test.productsmicroservice.commands.CreateProductCommand;
import com.test.productsmicroservice.dtos.CreateProductDto;

@RestController
@RequestMapping("/products")
public class ProductsController {

  private final CommandGateway commandGateway;

  @Autowired
  public ProductsController(CommandGateway commandGateway) {
    this.commandGateway = commandGateway;
  }

  @PostMapping
  String createProduct(@Valid @RequestBody CreateProductDto createProductDto) {

    CreateProductCommand createProductCommand = CreateProductCommand.builder().price(createProductDto.getPrice())
        .quantity(createProductDto.getQuantity()).title(createProductDto.getTitle())
        .productId(UUID.randomUUID().toString()).build();

    String returnValue = commandGateway.sendAndWait(createProductCommand);

    return returnValue;
  }
}