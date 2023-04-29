package com.cognizant.microservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;



import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(
		info=@Info(
				title = "IRDA Module",
				description = "This is an IRDA Module"
				)
		)
public class IrdaMicroserviceApplication {

	
	private static final Logger LOGGER = LoggerFactory.getLogger(IrdaMicroserviceApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(IrdaMicroserviceApplication.class, args);
		LOGGER.info("Inside main");
	}
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/api/v1/irda/**").allowedOrigins("http://localhost:4200");
			}
		};
	}

}
