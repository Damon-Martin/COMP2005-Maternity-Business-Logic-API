package uk.md.MaternityAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

@SpringBootApplication
public class MaternityApiApplication {
	// Swagger: http://localhost:8080/swagger-ui.html
	// Port 8080: Set in Application Properties
	public static void main(String[] args) {
		SpringApplication.run(MaternityApiApplication.class, args);
	}
}
