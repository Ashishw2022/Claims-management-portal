package com.mfpe.surveyor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.CrossOrigin;
@CrossOrigin()
@SpringBootApplication
public class SurveyorApplication extends SpringBootServletInitializer  {

	public static void main(String[] args) {
		SpringApplication.run(SurveyorApplication.class, args);
	}
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(SurveyorApplication.class);
	}
}
