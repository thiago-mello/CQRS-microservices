package com.test.productsmicroservice.controllers;

import java.util.List;

import com.test.productsmicroservice.query.FindProductsQuery;
import com.test.productsmicroservice.query.models.ProductRestModel;

import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductsQueryController {

  @Autowired
  QueryGateway queryGateway;

  @GetMapping
  public List<ProductRestModel> getProducts() {
    FindProductsQuery query = new FindProductsQuery();
    List<ProductRestModel> products = queryGateway
        .query(query, ResponseTypes.multipleInstancesOf(ProductRestModel.class)).join();

    return products;
  }

}
