package nl.devheaven.service.exceptions;

/**
 * This class represents a forbidden error in the system.
 */
public class ForbiddenException extends Exception {

    /**
     * Constructor for the forbidden exception.
     *
     * @param message the message of the exception.
     */
    public ForbiddenException(String message) {
        super(message);
    }

}