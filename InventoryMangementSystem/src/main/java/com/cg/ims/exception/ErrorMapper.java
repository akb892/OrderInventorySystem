package com.cg.ims.exception;

import org.springframework.http.HttpStatus;

/**
 * A utility class that maps error details for exception handling.
 * Typically used for creating error responses in a standardized format.
 */
public class ErrorMapper {

    /**
     * The HTTP status code associated with the error.
     */
    private HttpStatus status;

    /**
     * A brief message describing the error.
     */
    private String message;

    /**
     * Detailed information about the error (e.g., root cause, context).
     */
    private String details;

    /**
     * Parameterized constructor to initialize all fields of the ErrorMapper.
     *
     * @param status  the HTTP status of the error.
     * @param message a brief error message.
     * @param details additional details about the error.
     */
    public ErrorMapper(HttpStatus status, String message, String details) {
        super();
        this.status = status;
        this.message = message;
        this.details = details;
    }

    /**
     * Gets the HTTP status code of the error.
     *
     * @return the HTTP status.
     */
    public HttpStatus getStatus() {
        return status;
    }

    /**
     * Sets the HTTP status code of the error.
     *
     * @param status the HTTP status.
     */
    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    /**
     * Gets the error message.
     *
     * @return the error message.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the error message.
     *
     * @param message the error message.
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Gets additional details about the error.
     *
     * @return the error details.
     */
    public String getDetails() {
        return details;
    }

    /**
     * Sets additional details about the error.
     *
     * @param details the error details.
     */
    public void setDetails(String details) {
        this.details = details;
    }
}

