package com.test.productsmicroservice.errorhandling.errors;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AppError {
  private String message;
  private Date timestamp;
}
