package com.abc.demo.videostreaming;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
	info = @Info(
		title = "Video Streaming Application",
		description = "Upload, Display, Like, Unline, Comment, Uncomment functionalies are implements on Videos.Add,Update,Delete functionalies are implements on Genre.Add,Delete Users",
		contact = @Contact(
			name = "Harsha Vardhan",
			email = "Harshavk911@gmail.com"
		),
		version = "1.0.0"
	)
)

@SpringBootApplication
public class VideostreamingApplication {

	public static void main(String[] args) {
		SpringApplication.run(VideostreamingApplication.class, args);
	}

}
