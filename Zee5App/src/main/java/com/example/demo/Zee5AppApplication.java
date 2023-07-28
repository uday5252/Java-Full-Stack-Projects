package com.example.demo;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@OpenAPIDefinition(info = @Info(title = "APIS Definition", description = "This document gives the description of the APIs related to the OTT Application", version = "1.0.0", contact = @Contact(name = "Sandeep Sunkavalli", email = "sandeep.sunkavalli@zee.com")))

@SpringBootApplication
public class Zee5AppApplication {

	public static void main(String[] args) {
		SpringApplication.run(Zee5AppApplication.class, args);
//		private ApiInfo apiInfo() {
//			return new ApiInfo(
//					"My REST API", //title
//					"Some custom description of API.", //description
//					"Version 1.0", //version
//					"Terms of service", //terms of service URL
//					new Contact("Bhanuka Dissanayake", "www.example.com", "myeaddress@company.com"),
//					"License of API", "API license URL", Collections.emptyList()); // contact info
//		}
	}

}
