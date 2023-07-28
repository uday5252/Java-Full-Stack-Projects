package com.project.ZeeWebApplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
		
		info = @Info(
				title = "VideosAPI application",
				description = "This document stores all the API endpoints required to access the User,Genre,Video,Like & Comment data",
				version = "1.0.1",
				contact = @Contact(name="bhargav",email="bhargav@gmail.com")
				)
		)

@SpringBootApplication
public class ZeeWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZeeWebApplication.class, args);
	}

}
