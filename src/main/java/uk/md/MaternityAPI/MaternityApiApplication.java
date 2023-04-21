package uk.md.MaternityAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MaternityApiApplication {
	// Swagger: http://localhost:8080/swagger-ui/
	// Port 8080: Set in Application Properties
	public static void main(String[] args) {
		SpringApplication.run(MaternityApiApplication.class, args);
	}
}
