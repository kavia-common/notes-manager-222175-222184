package com.example.notesbackend.config;

import com.example.notesbackend.model.Note;
import com.example.notesbackend.repository.NoteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * PUBLIC_INTERFACE
 * Initializes sample data on startup when repository is empty.
 */
@Configuration
public class DataInitializer {

    private static final Logger log = LoggerFactory.getLogger(DataInitializer.class);

    @Bean
    CommandLineRunner seedNotes(NoteRepository repository) {
        return args -> {
            if (repository.count() == 0) {
                log.info("Seeding sample notes...");
                List<Note> notes = List.of(
                        new Note(null, "Welcome to Notes", "This is your first note."),
                        new Note(null, "Todo", "1. Build UI\n2. Connect to API\n3. Test features"),
                        new Note(null, "Ideas", "Consider adding tags and search.")
                );
                repository.saveAll(notes);
                log.info("Seeded {} notes", repository.count());
            } else {
                log.info("Notes already present: {}", repository.count());
            }
        };
    }
}
