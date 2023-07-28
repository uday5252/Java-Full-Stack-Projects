package com.abc.demo.END_PROJECT;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;


@OpenAPIDefinition(
		info = @Info(
			title="This is a Video Streaming API application",
			description="This document stores all the API endpoints required to access videos,likes,comments and genres",
			version="1.0.0",
			contact=@Contact(name="PavanTeja",email="pavan2119@gmail.com")
		)
	)
@SpringBootApplication
public class EndProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(EndProjectApplication.class, args);
	}

}
