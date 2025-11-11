package com.example.notesbackend;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "Notes Manager API",
                version = "1.0.0",
                description = "RESTful API for managing notes with CRUD operations, pagination and sorting.",
                contact = @Contact(name = "Notes Manager", email = "support@example.com")
        )
)
public class NotesBackendApplication {

    private static final Logger log = LoggerFactory.getLogger(NotesBackendApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(NotesBackendApplication.class, args);
        log.info("Notes Manager backend started on port 3001");
    }

    @Bean
    CommandLineRunner startupLog() {
        return args -> log.info("Application initialized. Swagger UI available at /swagger-ui.html");
    }
}
