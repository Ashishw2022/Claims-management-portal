package com.cts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(
		info=@Info(
				title = "Insure Module",
				description = "This is an Insure Module"
				)
		)
public class InsurerManagementServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InsurerManagementServiceApplication.class, args);
	}

}
