package com.example.usermanagement.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CountryFranceValidator implements ConstraintValidator<CountryFrance, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && value.equalsIgnoreCase("France");
    }
}
