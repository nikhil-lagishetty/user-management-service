package com.example.usermanagement.exception;

/**
 * Exception thrown when attempting to register a user with an email
 * that already exists in the system.
 */
public class DuplicateEmailException extends RuntimeException {

    /**
     * Constructs a new DuplicateEmailException with the specified detail message.
     *
     * @param message The detail message indicating the email conflict.
     */
    public DuplicateEmailException(String message) {
        super(message);
    }
}