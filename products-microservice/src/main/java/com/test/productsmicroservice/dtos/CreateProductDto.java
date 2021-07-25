package com.test.productsmicroservice.dtos;

import java.util.Objects;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class CreateProductDto {

  @NotBlank(message = "Title should not be empty.")
  private String title;

  @Min(value = 0, message = "Price should be positive.")
  private Double price;

  @Min(value = 1, message = "Quantity should be at least 1.")
  private Integer quantity;

  public CreateProductDto() {
  }

  public CreateProductDto(String title, Double price, Integer quantity) {
    this.title = title;
    this.price = price;
    this.quantity = quantity;
  }

  public String getTitle() {
    return this.title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Double getPrice() {
    return this.price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  public Integer getQuantity() {
    return this.quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this)
      return true;
    if (!(o instanceof CreateProductDto)) {
      return false;
    }
    CreateProductDto createProductDto = (CreateProductDto) o;
    return Objects.equals(title, createProductDto.title) && Objects.equals(price, createProductDto.price)
        && Objects.equals(quantity, createProductDto.quantity);
  }

  @Override
  public int hashCode() {
    return Objects.hash(title, price, quantity);
  }

}