package com.test.productsmicroservice.errorhandling;

import java.util.Date;

import com.test.productsmicroservice.errorhandling.errors.AppError;

import org.apache.http.HttpStatus;
import org.axonframework.commandhandling.CommandExecutionException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ProductErrorHandling {

  @ExceptionHandler(value = { CommandExecutionException.class })
  public ResponseEntity<Object> handleCommandError(CommandExecutionException exception) {
    AppError errorObject = new AppError(exception.getMessage(), new Date());

    return new ResponseEntity<>(errorObject, new HttpHeaders(), HttpStatus.SC_INTERNAL_SERVER_ERROR);
  }

}
