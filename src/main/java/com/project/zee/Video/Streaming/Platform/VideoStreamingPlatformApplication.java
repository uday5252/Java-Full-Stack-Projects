package com.project.zee.Video.Streaming.Platform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;


@OpenAPIDefinition(

        

        info = @Info(

                title = "Zee Video Streaming Platform ",

                description = "This document stores all the API endpoints required to access the Users,Genres,Videos,Likes & Comments data",

                version = "1.0.1",

                contact = @Contact(name="Pragna Lahari",email="pragna.bulusu@zee.com")

                )

        )

@SpringBootApplication
public class VideoStreamingPlatformApplication {
	

	public static void main(String[] args) {
		SpringApplication.run(VideoStreamingPlatformApplication.class, args);
	}

}
