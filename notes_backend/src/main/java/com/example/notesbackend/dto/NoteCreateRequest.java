package com.example.notesbackend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Request payload for creating a Note.
 */
// PUBLIC_INTERFACE
public class NoteCreateRequest {
    /** Title for the note. Required, 1-200 characters. */
    @NotBlank(message = "title is required")
    @Size(min = 1, max = 200, message = "title length must be between 1 and 200")
    private String title;

    /** Optional content for the note, up to 10,000 characters. */
    @Size(max = 10000, message = "content length must be <= 10000")
    private String content;

    public NoteCreateRequest() {}

    public NoteCreateRequest(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public NoteCreateRequest setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getContent() {
        return content;
    }

    public NoteCreateRequest setContent(String content) {
        this.content = content;
        return this;
    }
}
