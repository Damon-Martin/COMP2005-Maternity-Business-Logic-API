package uk.md.MaternityCalculationsAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
	// Swagger: http://localhost:8080/swagger-ui/
	// Port 8080: Set in Application Properties
	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}
}
