package com.test.productsmicroservice;

import com.test.productsmicroservice.commands.interceptors.CreateProductCommandInterceptor;

import org.axonframework.commandhandling.CommandBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ApplicationContext;

@EnableDiscoveryClient
@SpringBootApplication
public class ProductsMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductsMicroserviceApplication.class, args);
	}

	@Autowired
	public void registerCommandInterceptor(ApplicationContext context, CommandBus commandBus) {
		CreateProductCommandInterceptor createProductCommandInterceptor = context
				.getBean(CreateProductCommandInterceptor.class);

		commandBus.registerDispatchInterceptor(createProductCommandInterceptor);
	}

}
