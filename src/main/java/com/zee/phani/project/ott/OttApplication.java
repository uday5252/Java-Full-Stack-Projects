package com.zee.phani.project.ott;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

/*
 * @author : Phani Teja Gaddam
 */

@OpenAPIDefinition(info = @Info(title = "OTT APP API Definition", description = "This document gives the description of the APIs related to the OTT Application", version = "1.0.0", contact = @Contact(name = "Phani Teja", email = "phani.gaddam@zee.com")))

@SpringBootApplication
public class OttApplication {

	public static void main(String[] args) {
		SpringApplication.run(OttApplication.class, args);
	}

}
