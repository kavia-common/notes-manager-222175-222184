package com.example.notesbackend.service;

import com.example.notesbackend.dto.NoteDtos;
import com.example.notesbackend.model.Note;
import com.example.notesbackend.repository.NoteRepository;
import com.example.notesbackend.web.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.UUID;

/**
 * PUBLIC_INTERFACE
 * Service encapsulating business logic for Notes.
 */
@Service
public class NoteService {

    private static final Logger log = LoggerFactory.getLogger(NoteService.class);

    private final NoteRepository repository;

    public NoteService(NoteRepository repository) {
        this.repository = repository;
    }

    public Page<Note> list(Pageable pageable) {
        log.debug("Listing notes: {}", pageable);
        return repository.findAll(pageable);
    }

    public Note get(UUID id) {
        log.debug("Fetching note {}", id);
        return repository.findById(id).orElseThrow(() ->
                new NotFoundException("Note with id " + id + " not found"));
    }

    public Note create(NoteDtos.CreateNoteRequest req) {
        validateTitle(req.getTitle());
        Note note = new Note(null, req.getTitle().trim(), trimToNull(req.getContent()));
        Note saved = repository.save(note);
        log.info("Created note {}", saved.getId());
        return saved;
    }

    public Note update(UUID id, NoteDtos.UpdateNoteRequest req) {
        validateTitle(req.getTitle());
        Note existing = get(id);
        existing.setTitle(req.getTitle().trim());
        existing.setContent(trimToNull(req.getContent()));
        Note saved = repository.save(existing);
        log.info("Updated note {}", saved.getId());
        return saved;
    }

    public void delete(UUID id) {
        Note existing = get(id);
        repository.delete(existing);
        log.info("Deleted note {}", id);
    }

    private void validateTitle(String title) {
        if (!StringUtils.hasText(title)) {
            throw new IllegalArgumentException("title must not be blank");
        }
        if (title.trim().length() > 200) {
            throw new IllegalArgumentException("title must be at most 200 characters");
        }
    }

    private String trimToNull(String s) {
        if (s == null) return null;
        String t = s.trim();
        return t.isEmpty() ? null : t;
    }
}
