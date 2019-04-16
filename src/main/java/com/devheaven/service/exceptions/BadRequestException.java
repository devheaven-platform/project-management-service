package com.devheaven.service.exceptions;

import java.util.Map;

/**
 * This class represents a BadRequest error in the system.
 */
public class BadRequestException extends Exception {

    private Map<String, String> errors;

    /**
     * Constructor for the bad request exception.
     *
     * @param message the message of the exception.
     */
    public BadRequestException(String message) {
        super(message);
    }

    /**
     * Constructor for the bad request exception.
     *
     * @param errors a map of validation errors.
     */
    public BadRequestException(Map<String, String> errors) {
        super("One or more values are invalid");
        this.errors = errors;
    }

    /**
     * A list of validation errors. The key is the field the
     * error occurred on and the value is the validation
     * message.
     *
     * @return a map of validation errors.
     */
    public Map<String, String> getErrors() {
        return errors;
    }

}
