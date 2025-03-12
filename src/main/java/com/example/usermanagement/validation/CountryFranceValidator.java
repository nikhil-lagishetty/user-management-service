package com.example.usermanagement.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * Validator class for validating if a user's country is France.
 * This class implements the {@link ConstraintValidator} interface and
 * performs the actual validation logic for the {@link CountryFrance} annotation.
 *
 * The {@link CountryFranceValidator} checks whether the value provided
 * for the country field is not null and equals "France" (case-insensitive).
 */
public class CountryFranceValidator implements ConstraintValidator<CountryFrance, String> {

    /**
     * Validates if the given country value is "France".
     *
     * @param value the country value to validate
     * @param context the context in which the constraint is evaluated
     * @return true if the country is "France", false otherwise
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // Check if the value is not null and equals "France" (case-insensitive)
        return value != null && value.equalsIgnoreCase("France");
    }
}
