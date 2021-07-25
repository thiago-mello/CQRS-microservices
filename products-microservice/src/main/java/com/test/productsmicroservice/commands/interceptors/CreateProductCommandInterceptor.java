package com.test.productsmicroservice.commands.interceptors;

import java.util.List;
import java.util.function.BiFunction;

import com.test.productsmicroservice.commands.CreateProductCommand;

import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.messaging.MessageDispatchInterceptor;
import org.springframework.stereotype.Component;

@Component
public class CreateProductCommandInterceptor implements MessageDispatchInterceptor<CommandMessage<?>> {

  @Override
  public BiFunction<Integer, CommandMessage<?>, CommandMessage<?>> handle(List<? extends CommandMessage<?>> messages) {

    return (index, command) -> {

      if (CreateProductCommand.class.equals(command.getPayload())) {
        CreateProductCommand createProductCommand = (CreateProductCommand) command.getPayload();

        if (createProductCommand.getPrice() < 0) {
          throw new IllegalArgumentException("Product price cannot be less than zero.");
        }

        if (createProductCommand.getTitle() == null || createProductCommand.getTitle().isBlank()) {
          throw new IllegalArgumentException("Product must have a title.");
        }
      }

      return command;
    };
  }

}
