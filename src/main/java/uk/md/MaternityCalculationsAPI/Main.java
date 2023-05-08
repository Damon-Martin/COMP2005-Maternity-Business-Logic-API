package uk.md.MaternityCalculationsAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import uk.md.MaternityCalculationsAPI.Controllers.GetApiEntities;
import uk.md.MaternityCalculationsAPI.Controllers.GetApiLists;
import uk.md.MaternityCalculationsAPI.Models.Entities.Admission;
import uk.md.MaternityCalculationsAPI.Models.Entities.Patient;

import java.io.IOException;
import java.util.List;

@SpringBootApplication
public class Main {
	// Swagger: http://localhost:8080/swagger-ui/
	// Port 8080: Set in Application Properties
	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}
}
