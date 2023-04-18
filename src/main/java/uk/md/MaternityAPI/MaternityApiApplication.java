package uk.md.MaternityAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MaternityApiApplication {

	public static void main(String[] args) {
		// Port 8080: Set in Application Properties
		SpringApplication.run(MaternityApiApplication.class, args);
	}

}
