package com.example.usermanagement.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CountryFranceValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface CountryFrance {
    String message() default "User must reside in France";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}