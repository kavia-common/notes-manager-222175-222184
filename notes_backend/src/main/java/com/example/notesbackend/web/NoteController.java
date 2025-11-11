package com.example.notesbackend.web;

import com.example.notesbackend.dto.NoteDtos;
import com.example.notesbackend.model.Note;
import com.example.notesbackend.service.NoteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * PUBLIC_INTERFACE
 * REST controller for Note resources.
 */
@RestController
@RequestMapping("/api/notes")
@Tag(name = "Notes", description = "CRUD operations for Notes")
public class NoteController {

    private final NoteService service;

    public NoteController(NoteService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "List notes", description = "Returns a paginated list of notes. Supports page, size and sort (e.g., sort=createdAt,desc).")
    public ResponseEntity<Page<NoteDtos.NoteResponse>> list(
            @Parameter(description = "Pagination and sorting parameters")
            @PageableDefault(size = 10)
            @SortDefault.SortDefaults({
                    @SortDefault(sort = "createdAt", direction = org.springframework.data.domain.Sort.Direction.DESC)
            }) Pageable pageable
    ) {
        Page<Note> page = service.list(pageable);
        Page<NoteDtos.NoteResponse> mapped = page.map(n ->
                new NoteDtos.NoteResponse(n.getId(), n.getTitle(), n.getContent(), n.getCreatedAt(), n.getUpdatedAt()));
        return ResponseEntity.ok(mapped);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get note by ID", description = "Retrieve a single note by UUID.")
    public ResponseEntity<NoteDtos.NoteResponse> get(
            @Parameter(description = "Note UUID") @PathVariable("id") UUID id
    ) {
        Note n = service.get(id);
        return ResponseEntity.ok(new NoteDtos.NoteResponse(n.getId(), n.getTitle(), n.getContent(), n.getCreatedAt(), n.getUpdatedAt()));
    }

    @PostMapping
    @Operation(summary = "Create note", description = "Create a new note.")
    public ResponseEntity<NoteDtos.NoteResponse> create(
            @Valid @RequestBody NoteDtos.CreateNoteRequest request
    ) {
        Note n = service.create(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new NoteDtos.NoteResponse(n.getId(), n.getTitle(), n.getContent(), n.getCreatedAt(), n.getUpdatedAt()));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update note", description = "Update an existing note by ID.")
    public ResponseEntity<NoteDtos.NoteResponse> update(
            @Parameter(description = "Note UUID") @PathVariable("id") UUID id,
            @Valid @RequestBody NoteDtos.UpdateNoteRequest request
    ) {
        Note n = service.update(id, request);
        return ResponseEntity.ok(new NoteDtos.NoteResponse(n.getId(), n.getTitle(), n.getContent(), n.getCreatedAt(), n.getUpdatedAt()));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete note", description = "Delete a note by ID.")
    public ResponseEntity<Void> delete(@Parameter(description = "Note UUID") @PathVariable("id") UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
