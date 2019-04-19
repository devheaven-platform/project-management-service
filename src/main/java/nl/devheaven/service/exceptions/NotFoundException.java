package nl.devheaven.service.exceptions;

/**
 * This class represents a NotFound error in the system.
 */
public class NotFoundException extends Exception {

    /**
     * Constructor for the not found exception.
     *
     * @param message the message of the exception.
     */
    public NotFoundException(String message) {
        super(message);
    }

}
