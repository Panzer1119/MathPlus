package de.codemakers.math.expression;

import java.util.List;
import java.util.stream.Collectors;

/**
 * ValidationResult
 *
 * @author Paul Hagedorn
 */
public class ValidationResult {

    private final boolean valid;
    private final List<String> errors;

    /**
     * Create a new instance
     *
     * @param valid Whether the validation of the expression was successful
     * @param errors The list of errors returned if the validation was
     * unsuccessful
     */
    public ValidationResult(boolean valid, List<String> errors) {
        this.valid = valid;
        this.errors = errors;
    }

    /**
     * Check if an expression has been validated successfully
     *
     * @return true if the validation was successful, false otherwise
     */
    public final boolean isValid() {
        return valid;
    }

    /**
     * Get the list of errors describing the issues while validating the
     * expression
     *
     * @return The List of errors
     */
    public final List<String> getErrors() {
        return errors;
    }

    @Override
    public final String toString() {
        if (errors == null) {
            return "";
        }
        return errors.stream().collect(Collectors.joining("\n"));
    }

    /**
     * A static class representing a successful validation result
     */
    public static final ValidationResult SUCCESS = new ValidationResult(true, null);

}
