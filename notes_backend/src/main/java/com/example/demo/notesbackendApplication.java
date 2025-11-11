package com.example.notesbackend;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Application entry point for Notes Backend.
 */
@OpenAPIDefinition(
        info = @Info(
                title = "Notes Manager API",
                version = "0.1.0",
                description = "REST API for managing notes (CRUD, pagination, search)."
        )
)
@SpringBootApplication
public class notesbackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(notesbackendApplication.class, args);
    }
}
