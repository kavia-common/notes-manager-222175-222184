package com.example.notesbackend.repository;

import com.example.notesbackend.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * PUBLIC_INTERFACE
 * JPA repository for Note entity.
 */
public interface NoteRepository extends JpaRepository<Note, UUID> {
}
