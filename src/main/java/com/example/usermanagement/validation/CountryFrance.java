package com.example.usermanagement.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

/**
 * Custom validation annotation for ensuring that a user's country is France.
 * This annotation can be applied to fields or parameters to validate that the
 * country is set to France. It uses the {@link CountryFranceValidator} to perform
 * the validation logic.
 *
 * Example usage:
 *
 * <pre>
 * {@code
 * @CountryFrance
 * private String country;
 * }
 * </pre>
 */
@Documented
@Constraint(validatedBy = CountryFranceValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface CountryFrance {

    /**
     * The default error message when the country is not France.
     *
     * @return the error message
     */
    String message() default "User must reside in France";

    /**
     * Allows grouping of constraints. This can be used to apply specific
     * validation groups to this constraint.
     *
     * @return the validation groups
     */
    Class<?>[] groups() default {};

    /**
     * Contains metadata about the constraint. This is used by validation
     * frameworks to pass additional information when validating.
     *
     * @return the payload
     */
    Class<? extends Payload>[] payload() default {};
}
