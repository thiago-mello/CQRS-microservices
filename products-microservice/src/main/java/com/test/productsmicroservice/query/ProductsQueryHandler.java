package com.test.productsmicroservice.query;

import java.util.ArrayList;
import java.util.List;

import com.test.productsmicroservice.entities.ProductEntity;
import com.test.productsmicroservice.query.models.ProductRestModel;
import com.test.productsmicroservice.repositories.ProductRepository;

import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductsQueryHandler {

  private ProductRepository productRepository;

  @Autowired
  public ProductsQueryHandler(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  @QueryHandler
  public List<ProductRestModel> findProducts(FindProductsQuery query) {
    List<ProductRestModel> products = new ArrayList<>();

    List<ProductEntity> storedProducts = this.productRepository.findAll();

    for (ProductEntity product : storedProducts) {
      ProductRestModel productModel = new ProductRestModel();
      BeanUtils.copyProperties(product, productModel);

      products.add(productModel);
    }

    return products;
  };

}
