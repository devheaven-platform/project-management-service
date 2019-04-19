package nl.devheaven.service.models;

import io.swagger.annotations.ApiModelProperty;

/**
 * This model represents a health check in the system.
 */
public class Health {

    @ApiModelProperty(required = true, value = "The message of the health check", example = "Service is running")
    private String message;

    /**
     * Constructor for the health model
     *
     * @param message the message of the health check.
     */
    public Health(String message) {
        this.message = message;
    }

    /**
     * Gets the message of the health check.
     *
     * @return the message of the health check.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the message of the health check.
     *
     * @param message the message to set.
     */
    public void setMessage(String message) {
        this.message = message;
    }

}
