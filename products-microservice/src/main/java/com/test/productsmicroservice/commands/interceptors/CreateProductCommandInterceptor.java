package com.test.productsmicroservice.commands.interceptors;

import java.util.List;
import java.util.function.BiFunction;

import com.test.productsmicroservice.commands.CreateProductCommand;
import com.test.productsmicroservice.entities.ProductLookupEntity;
import com.test.productsmicroservice.repositories.ProductLookupRepository;

import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.messaging.MessageDispatchInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class CreateProductCommandInterceptor implements MessageDispatchInterceptor<CommandMessage<?>> {

  private ProductLookupRepository productLookupRepository;

  @Autowired
  public CreateProductCommandInterceptor(ProductLookupRepository productLookupRepository) {
    this.productLookupRepository = productLookupRepository;
  }

  @Override
  public BiFunction<Integer, CommandMessage<?>, CommandMessage<?>> handle(List<? extends CommandMessage<?>> messages) {

    return (index, command) -> {

      if (CreateProductCommand.class.equals(command.getPayloadType())) {
        CreateProductCommand createProductCommand = (CreateProductCommand) command.getPayload();

        ProductLookupEntity product = this.productLookupRepository.findByIdOrTitle(createProductCommand.getProductId(),
            createProductCommand.getTitle());

        if (product != null) {
          throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product already exists in Database.");
        }

        if (createProductCommand.getPrice() < 0) {
          throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product price cannot be less than zero.");
        }

        if (createProductCommand.getTitle() == null || createProductCommand.getTitle().isBlank()) {
          throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product must have a title.");
        }
      }

      return command;
    };
  }

}
