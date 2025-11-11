package com.example.notesbackend.mapper;

import com.example.notesbackend.dto.NoteCreateRequest;
import com.example.notesbackend.dto.NoteResponse;
import com.example.notesbackend.dto.NoteUpdateRequest;
import com.example.notesbackend.model.Note;

/**
 * Mapper to convert between Note entity and DTOs.
 */
public class NoteMapper {

    public static Note toEntity(NoteCreateRequest req) {
        Note note = Note.newWithId();
        note.setTitle(req.getTitle());
        note.setContent(req.getContent());
        return note;
    }

    public static void updateEntity(Note note, NoteUpdateRequest req) {
        note.setTitle(req.getTitle());
        note.setContent(req.getContent());
    }

    public static NoteResponse toResponse(Note note) {
        return new NoteResponse()
                .setId(note.getId())
                .setTitle(note.getTitle())
                .setContent(note.getContent())
                .setCreatedAt(note.getCreatedAt())
                .setUpdatedAt(note.getUpdatedAt());
    }
}
