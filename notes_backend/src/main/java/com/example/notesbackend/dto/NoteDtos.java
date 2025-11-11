package com.example.notesbackend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.Instant;
import java.util.UUID;

/**
 * Aggregated DTO classes for Note API.
 */
public class NoteDtos {

    // PUBLIC_INTERFACE
    @Schema(name = "CreateNoteRequest", description = "Payload to create a new note")
    public static class CreateNoteRequest {
        @Schema(description = "Title of the note", example = "My first note", maxLength = 200)
        @NotBlank(message = "title must not be blank")
        @Size(max = 200, message = "title must be at most 200 characters")
        private String title;

        @Schema(description = "Content of the note", example = "Some detailed content", maxLength = 5000, nullable = true)
        @Size(max = 5000, message = "content must be at most 5000 characters")
        private String content;

        public CreateNoteRequest() {}

        public String getTitle() { return title; }
        public void setTitle(String title) { this.title = title; }

        public String getContent() { return content; }
        public void setContent(String content) { this.content = content; }
    }

    // PUBLIC_INTERFACE
    @Schema(name = "UpdateNoteRequest", description = "Payload to update an existing note")
    public static class UpdateNoteRequest {
        @Schema(description = "Title of the note", example = "Updated title", maxLength = 200)
        @NotBlank(message = "title must not be blank")
        @Size(max = 200, message = "title must be at most 200 characters")
        private String title;

        @Schema(description = "Content of the note", example = "Updated content", maxLength = 5000, nullable = true)
        @Size(max = 5000, message = "content must be at most 5000 characters")
        private String content;

        public UpdateNoteRequest() {}

        public String getTitle() { return title; }
        public void setTitle(String title) { this.title = title; }

        public String getContent() { return content; }
        public void setContent(String content) { this.content = content; }
    }

    // PUBLIC_INTERFACE
    @Schema(name = "NoteResponse", description = "Note resource response")
    public static class NoteResponse {
        @Schema(description = "Unique identifier", example = "d290f1ee-6c54-4b01-90e6-d701748f0851")
        private UUID id;

        @Schema(description = "Title", example = "My note")
        private String title;

        @Schema(description = "Content", example = "Content here", nullable = true)
        private String content;

        @Schema(description = "Creation timestamp (UTC)")
        private Instant createdAt;

        @Schema(description = "Last update timestamp (UTC)")
        private Instant updatedAt;

        public NoteResponse() {}

        public NoteResponse(UUID id, String title, String content, Instant createdAt, Instant updatedAt) {
            this.id = id;
            this.title = title;
            this.content = content;
            this.createdAt = createdAt;
            this.updatedAt = updatedAt;
        }

        public UUID getId() { return id; }
        public void setId(UUID id) { this.id = id; }
        public String getTitle() { return title; }
        public void setTitle(String title) { this.title = title; }
        public String getContent() { return content; }
        public void setContent(String content) { this.content = content; }
        public Instant getCreatedAt() { return createdAt; }
        public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }
        public Instant getUpdatedAt() { return updatedAt; }
        public void setUpdatedAt(Instant updatedAt) { this.updatedAt = updatedAt; }
    }

    // PUBLIC_INTERFACE
    @Schema(name = "ErrorResponse", description = "Standard error payload")
    public static class ErrorResponse {
        private String message;
        private String error;
        private Integer status;
        private String path;
        private Instant timestamp;

        public ErrorResponse() {}

        public ErrorResponse(String message, String error, Integer status, String path, Instant timestamp) {
            this.message = message;
            this.error = error;
            this.status = status;
            this.path = path;
            this.timestamp = timestamp;
        }

        public String getMessage() { return message; }
        public void setMessage(String message) { this.message = message; }
        public String getError() { return error; }
        public void setError(String error) { this.error = error; }
        public Integer getStatus() { return status; }
        public void setStatus(Integer status) { this.status = status; }
        public String getPath() { return path; }
        public void setPath(String path) { this.path = path; }
        public Instant getTimestamp() { return timestamp; }
        public void setTimestamp(Instant timestamp) { this.timestamp = timestamp; }
    }
}
