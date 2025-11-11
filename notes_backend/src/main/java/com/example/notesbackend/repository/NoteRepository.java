package com.example.notesbackend.repository;

import com.example.notesbackend.model.Note;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data repository for Note entity.
 */
// PUBLIC_INTERFACE
public interface NoteRepository extends JpaRepository<Note, String> {
    Page<Note> findByTitleContainingIgnoreCase(String title, Pageable pageable);
}
