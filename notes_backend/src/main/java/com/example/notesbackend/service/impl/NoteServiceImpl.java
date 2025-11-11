package com.example.notesbackend.service.impl;

import com.example.notesbackend.dto.NoteCreateRequest;
import com.example.notesbackend.dto.NoteResponse;
import com.example.notesbackend.dto.NoteUpdateRequest;
import com.example.notesbackend.dto.PagedResponse;
import com.example.notesbackend.exception.NotFoundException;
import com.example.notesbackend.mapper.NoteMapper;
import com.example.notesbackend.model.Note;
import com.example.notesbackend.repository.NoteRepository;
import com.example.notesbackend.service.NoteService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 * Default implementation of NoteService.
 */
@Service
public class NoteServiceImpl implements NoteService {

    private final NoteRepository repo;

    public NoteServiceImpl(NoteRepository repo) {
        this.repo = repo;
    }

    @Override
    public NoteResponse create(NoteCreateRequest request) {
        Note entity = NoteMapper.toEntity(request);
        Note saved = repo.save(entity);
        return NoteMapper.toResponse(saved);
    }

    @Override
    public NoteResponse getById(String id) {
        Note note = repo.findById(id).orElseThrow(() -> new NotFoundException("Note not found: " + id));
        return NoteMapper.toResponse(note);
    }

    @Override
    public PagedResponse<NoteResponse> list(int page, int size, String query) {
        PageRequest pr = PageRequest.of(Math.max(page, 0), Math.min(Math.max(size, 1), 200));
        Page<Note> results;
        if (query != null && !query.isBlank()) {
            results = repo.findByTitleContainingIgnoreCase(query.trim(), pr);
        } else {
            results = repo.findAll(pr);
        }
        return new PagedResponse<>(
                results.getContent().stream().map(NoteMapper::toResponse).toList(),
                results.getNumber(),
                results.getSize(),
                results.getTotalElements()
        );
    }

    @Override
    public NoteResponse update(String id, NoteUpdateRequest request) {
        Note note = repo.findById(id).orElseThrow(() -> new NotFoundException("Note not found: " + id));
        NoteMapper.updateEntity(note, request);
        Note saved = repo.save(note);
        return NoteMapper.toResponse(saved);
    }

    @Override
    public void delete(String id) {
        if (!repo.existsById(id)) {
            throw new NotFoundException("Note not found: " + id);
        }
        repo.deleteById(id);
    }
}
