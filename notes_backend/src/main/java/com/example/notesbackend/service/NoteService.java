package com.example.notesbackend.service;

import com.example.notesbackend.dto.NoteCreateRequest;
import com.example.notesbackend.dto.NoteResponse;
import com.example.notesbackend.dto.NoteUpdateRequest;
import com.example.notesbackend.dto.PagedResponse;

/**
 * Service interface for managing notes.
 */
// PUBLIC_INTERFACE
public interface NoteService {
    NoteResponse create(NoteCreateRequest request);
    NoteResponse getById(String id);
    PagedResponse<NoteResponse> list(int page, int size, String query);
    NoteResponse update(String id, NoteUpdateRequest request);
    void delete(String id);
}
