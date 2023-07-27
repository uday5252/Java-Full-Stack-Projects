package com.sivasai.endGame.SpringFinalProject;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
		info = @Info(title = "Watchflix APi definition",
				description = "These are the endpoints in the api definition"
		)
)
@SpringBootApplication
public class SpringFinalProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringFinalProjectApplication.class, args);
	}

}
