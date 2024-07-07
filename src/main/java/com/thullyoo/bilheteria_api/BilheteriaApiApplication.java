package com.thullyoo.bilheteria_api;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "API REST Bilheteria ", version = "1.0", description = "API REST de sistema de bilheteria para cinemas."))
public class BilheteriaApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BilheteriaApiApplication.class, args);
	}

}
