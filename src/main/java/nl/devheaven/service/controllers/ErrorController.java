package nl.devheaven.service.controllers;

import nl.devheaven.service.exceptions.BadRequestException;
import nl.devheaven.service.exceptions.ForbiddenException;
import nl.devheaven.service.exceptions.InternalServerException;
import nl.devheaven.service.exceptions.NotFoundException;
import nl.devheaven.service.models.Error;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * This controller is used as a global exception handler. It processes
 * all the exceptions thrown in the application.
 */
@RestControllerAdvice
public class ErrorController {

    private final Logger LOG = LoggerFactory.getLogger(ErrorController.class);

    // TODO: add handler for Forbidden

    /**
     * This method handles the BadRequest (400) exceptions. An list of errors will be provided
     * if the exception was triggered by an failed validation attempt.
     *
     * @param ex the exception object
     * @return Error containing the name, message and status code of the error.
     */
    @ExceptionHandler({HttpMessageNotReadableException.class, BadRequestException.class})
    public ResponseEntity<Error> handleHttpMessageNotReadableException(Exception ex) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        if (ex instanceof BadRequestException) {
            return new ResponseEntity<>(new Error(ex.getMessage(), ((BadRequestException) ex).getErrors()), headers, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(new Error(ex.getMessage()), headers, HttpStatus.BAD_REQUEST);
    }

    /**
     * This method handles the Unauthorized (401) exceptions.
     *
     * @return Error containing the name, message and status code of the error.
     */
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Error> handleAccessDeniedException() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return new ResponseEntity<>(new Error("Your not authorized to access this resource"), headers, HttpStatus.UNAUTHORIZED);
    }

    /**
     * This method handles the Forbidden (403) exceptions.
     *
     * @return Error containing the name, message and status code of the error.
     */
    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<Error> handleForbiddenException(ForbiddenException ex) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return new ResponseEntity<>(new Error(ex.getMessage()), headers, HttpStatus.FORBIDDEN);
    }

    /**
     * This method handles the NotFound (404) exceptions.
     *
     * @param ex the exception object
     * @return Error containing the name, message and status code of the error.
     */
    @ExceptionHandler({NoHandlerFoundException.class, NotFoundException.class})
    public ResponseEntity<Error> handleNoHandlerFoundException(Exception ex) {
        String message = ex instanceof NotFoundException ? ex.getMessage() : "Resource not found";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return new ResponseEntity<>(new Error(message), headers, HttpStatus.NOT_FOUND);
    }

    /**
     * This method handles the MethodNotAllowed (405) exceptions.
     *
     * @return Error containing the name, message and status code of the error.
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<Error> handleHttpRequestMethodNotSupportedException() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return new ResponseEntity<>(new Error("The request method is not allowed"), headers, HttpStatus.METHOD_NOT_ALLOWED);
    }

    /**
     * This method handles the NotAcceptable (406) exceptions.
     *
     * @return Error containing the name, message and status code of the error.
     */
    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    public ResponseEntity<Error> handleHttpMediaTypeNotAcceptableException() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return new ResponseEntity<>(new Error("The request is not acceptable"), headers, HttpStatus.NOT_ACCEPTABLE);
    }

    /**
     * This method handles the InternalServerError (500) exceptions.
     *
     * @param ex the exception object
     * @return Error containing the name, message and status code of the error.
     */
    @ExceptionHandler(InternalServerException.class)
    public ResponseEntity<Error> handleInternalServerException(Exception ex) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return new ResponseEntity<>(new Error(ex.getMessage()), headers, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * This method handles the NotImplemented (501) exceptions.
     *
     * @return Error containing the name, message and status code of the error.
     */
    @ExceptionHandler(UnsupportedOperationException.class)
    public ResponseEntity<Error> handleUnsupportedOperationException() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return new ResponseEntity<>(new Error("The server either does not recognize the request method or lacks the ability to fulfill the request"), headers, HttpStatus.NOT_IMPLEMENTED);
    }

    /**
     * This method catches all other exceptions and responds with a InternalServerError.
     * The unknown exception will be printed to stderr.
     *
     * @param ex the exception object
     * @return Error containing the name, message and status code of the error.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Error> handleException(Exception ex) {
        LOG.error("An unknown exception occurred", ex);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return new ResponseEntity<>(new Error("An internal server error occurred"), headers, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
