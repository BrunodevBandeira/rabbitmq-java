package com.rabbitmq.rabbitmq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(info = @Info(title = "Rabbitmq-Java", version = "1", description = "API Desenvolvida para mostrar meu conhecimento com RabbitMQ"))
@SpringBootApplication
public class RabbitmqApplication {

	public static void main(String[] args) {
		SpringApplication.run(RabbitmqApplication.class, args);
	}
	// https://www.youtube.com/watch?v=rRupk_WUIBI&list=PLyUWi6NKA47v55hInmFDnT6lNB6puAuwF&index=54&ab_channel=CaioRodrigues-BackEndJava
	//  01:05
}
