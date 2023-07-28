package com.project.demo.end_to_end;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "OTT api documentation",description = "This document stores api endpoints required to upload,"+
"update and access videos and genres",contact = @Contact(name = "Eshwar Pendem", email = "eshwarpendem@gmaill.com")))
public class EndToEndApplication {

	public static void main(String[] args) {
		SpringApplication.run(EndToEndApplication.class, args);
	}

}
