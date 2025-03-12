package com.example.usermanagement.dto;

import com.example.usermanagement.validation.CountryFrance;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) representing the user registration details.
 * Used to capture and validate user input when registering a new user.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegistrationDTO {

    /**
     * The name of the user.
     * This field is mandatory and cannot be blank.
     */
    @NotBlank(message = "Name is required")
    private String name;

    /**
     * The age of the user.
     * The value must be at least 18 years.
     * This field is validated to ensure the user is an adult.
     */
    @Min(value = 18, message = "Age must be at least 18")
    private int age;

    /**
     * The country of the user.
     * This field is mandatory and cannot be blank.
     */
    @NotBlank(message = "Country is required")
    @CountryFrance(message = "User must reside in France")
    private String country;

    /**
     * The email address of the user.
     * This field is mandatory, cannot be blank, and must be a valid email format.
     */
    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    private String email;

    /**
     * The phone number of the user.
     * This field is mandatory and cannot be blank.
     */
    @NotBlank(message = "Phone number is required")
    private String phone;
}
