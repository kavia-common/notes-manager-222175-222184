package com.example.notesbackend.controller;

import com.example.notesbackend.dto.NoteCreateRequest;
import com.example.notesbackend.dto.NoteResponse;
import com.example.notesbackend.dto.NoteUpdateRequest;
import com.example.notesbackend.dto.PagedResponse;
import com.example.notesbackend.service.NoteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller exposing CRUD operations for Notes.
 */
@RestController
@RequestMapping("/api/notes")
@Tag(name = "Notes", description = "CRUD operations for notes")
public class NoteController {

    private final NoteService service;

    public NoteController(NoteService service) {
        this.service = service;
    }

    /**
     * Create a new note.
     *
     * @param request NoteCreateRequest containing title and optional content
     * @return created note representation
     */
    // PUBLIC_INTERFACE
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create note", description = "Creates a new note with title and optional content. Returns 201.")
    public NoteResponse create(@Valid @RequestBody NoteCreateRequest request) {
        return service.create(request);
    }

    /**
     * Get note by ID.
     *
     * @param id note UUID
     * @return note representation
     */
    // PUBLIC_INTERFACE
    @GetMapping("/{id}")
    @Operation(summary = "Get note", description = "Fetches a note by ID. Returns 404 if not found.")
    public NoteResponse getById(@PathVariable("id") String id) {
        return service.getById(id);
    }

    /**
     * List notes with pagination and optional search by title (q).
     *
     * @param page zero-based page index
     * @param size page size
     * @param q    optional search query to match title (contains, case-insensitive)
     * @return paged list of notes
     */
    // PUBLIC_INTERFACE
    @GetMapping
    @Operation(summary = "List notes", description = "Returns notes with pagination and optional search by title (?q=).")
    public PagedResponse<NoteResponse> list(
            @RequestParam(name = "page", defaultValue = "0")
            @Parameter(description = "Zero-based page index") int page,
            @RequestParam(name = "size", defaultValue = "10")
            @Parameter(description = "Page size") int size,
            @RequestParam(name = "q", required = false)
            @Parameter(description = "Search string for title (contains, case-insensitive)") String q
    ) {
        return service.list(page, size, q);
    }

    /**
     * Update a note.
     *
     * @param id      note UUID
     * @param request NoteUpdateRequest containing new title/content
     * @return updated note
     */
    // PUBLIC_INTERFACE
    @PutMapping("/{id}")
    @Operation(summary = "Update note", description = "Updates the note title/content. Returns 404 if not found.")
    public NoteResponse update(@PathVariable("id") String id, @Valid @RequestBody NoteUpdateRequest request) {
        return service.update(id, request);
    }

    /**
     * Delete a note.
     *
     * @param id note UUID
     */
    // PUBLIC_INTERFACE
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete note", description = "Deletes a note by ID. Returns 204 on success, 404 if not found.")
    public void delete(@PathVariable("id") String id) {
        service.delete(id);
    }
}
