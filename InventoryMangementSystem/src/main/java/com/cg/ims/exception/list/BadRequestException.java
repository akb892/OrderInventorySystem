package com.cg.ims.exception.list;

/**
 * Custom exception class that represents a bad request scenario.
 * Extends the built-in Exception class.
 */
public class BadRequestException extends Exception {

    // Serial version UID for serialization compatibility
    private static final long serialVersionUID = 1L;
    
    /**
     * Constructor to create an instance of BadRequestException with a custom message.
     * 
     * @param msg The message to be associated with the exception.
     */
    public BadRequestException(String msg) {
        super(msg);  // Pass the message to the parent Exception class
    }
}

