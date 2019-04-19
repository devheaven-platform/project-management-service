package nl.devheaven.service.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import io.swagger.annotations.ApiModelProperty;

import java.util.Map;

/**
 * This model represents a error in the system.
 */
@JsonInclude(Include.NON_NULL)
public class Error {

    @ApiModelProperty(required = true, value = "The message of the error", example = "Resource not found")
    private final String message;

    @ApiModelProperty(value = "An map of validation errors")
    private final Map<String, String> errors;

    /**
     * Constructor for the error model.
     *
     * @param message the message of the error.
     */
    public Error(String message) {
        this.message = message;
        this.errors = null;
    }

    /**
     * Constructor for the error model.
     *
     * @param message the message of the error.
     * @param errors  a map of validation errors.
     */
    public Error(String message, Map<String, String> errors) {
        this.message = message;
        this.errors = errors;
    }

    /**
     * Gets the message of the error.
     *
     * @return the message of the error.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Gets the map of validation errors.
     *
     * @return the map of validation errors.
     */
    public Map<String, String> getErrors() {
        return errors;
    }

}
