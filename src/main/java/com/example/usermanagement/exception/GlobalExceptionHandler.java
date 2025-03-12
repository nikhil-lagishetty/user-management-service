package com.example.usermanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * Global exception handler to manage exceptions across the application.
 * This class uses @ControllerAdvice to handle various types of exceptions
 * globally and provide appropriate error responses to the client.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles validation exceptions when input data is not valid.
     * This handler captures errors that occur during the validation of request parameters or bodies.
     *
     * @param ex The exception containing validation errors.
     * @return A ResponseEntity with a map of field names and validation error messages.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        // Collecting field-specific validation error messages
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));
        // Returning a bad request response with the validation errors
        return ResponseEntity.badRequest().body(errors);
    }

    /**
     * Handles the case when a user is not found.
     * This handler captures custom exceptions thrown when a user is not found in the system.
     *
     * @param ex The exception containing the error message.
     * @return A ResponseEntity with a NOT_FOUND status and the exception message.
     */
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFound(UserNotFoundException ex) {
        // Returning a not found response with the user not found message
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    /**
     * Handles generic exceptions that are not caught by other specific handlers.
     * This handler ensures that an unexpected exception will still return a response to the client.
     *
     * @param ex The generic exception that was thrown.
     * @return A ResponseEntity with an INTERNAL_SERVER_ERROR status and a generic error message.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        // Returning a generic error response with the exception message
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("An unexpected error occurred: " + ex.getMessage());
    }
}
