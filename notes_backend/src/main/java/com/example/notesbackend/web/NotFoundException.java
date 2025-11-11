package com.example.notesbackend.web;

/**
 * PUBLIC_INTERFACE
 * Exception thrown when a requested resource is not found.
 */
public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
