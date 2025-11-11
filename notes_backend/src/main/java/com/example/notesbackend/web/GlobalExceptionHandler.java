package com.example.notesbackend.web;

import com.example.notesbackend.dto.NoteDtos;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

/**
 * PUBLIC_INTERFACE
 * Global exception handler to standardize error responses.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<NoteDtos.ErrorResponse> handleNotFound(NotFoundException ex, HttpServletRequest req) {
        log.warn("Not found: {}", ex.getMessage());
        return buildError(ex.getMessage(), "Not Found", HttpStatus.NOT_FOUND, req.getRequestURI());
    }

    @ExceptionHandler({
            IllegalArgumentException.class,
            MethodArgumentNotValidException.class,
            BindException.class,
            ConstraintViolationException.class,
            HttpMessageNotReadableException.class
    })
    public ResponseEntity<NoteDtos.ErrorResponse> handleBadRequest(Exception ex, HttpServletRequest req) {
        String message = ex.getMessage();
        log.warn("Bad request: {}", message);
        return buildError(message, "Bad Request", HttpStatus.BAD_REQUEST, req.getRequestURI());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<NoteDtos.ErrorResponse> handleGeneric(Exception ex, HttpServletRequest req) {
        log.error("Unexpected error", ex);
        return buildError("Internal server error", "Internal Server Error",
                HttpStatus.INTERNAL_SERVER_ERROR, req.getRequestURI());
    }

    private ResponseEntity<NoteDtos.ErrorResponse> buildError(String message, String error, HttpStatus status, String path) {
        NoteDtos.ErrorResponse payload = new NoteDtos.ErrorResponse(
                message, error, status.value(), path, Instant.now()
        );
        return ResponseEntity.status(status).body(payload);
    }
}
