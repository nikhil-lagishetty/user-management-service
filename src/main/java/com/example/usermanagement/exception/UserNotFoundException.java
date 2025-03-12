package com.example.usermanagement.exception;

/**
 * Custom exception class to represent the scenario when a user is not found.
 * This exception is thrown when a user could not be located in the system
 * based on the provided identifier (e.g., user ID).
 */
public class UserNotFoundException extends RuntimeException {

    /**
     * Constructor for the UserNotFoundException class.
     * This constructor takes a message that explains the reason for the exception.
     *
     * @param message The error message that provides details about the exception.
     */
    public UserNotFoundException(String message) {
        super(message);
    }
}
