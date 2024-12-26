package com.cg.ims.exception;

/**
 * Custom exception class to represent an internal server error scenario.
 * This class extends the built-in Exception class.
 */
public class InternalServerErrorException extends Exception {

    // Serial version UID for serialization compatibility
    private static final long serialVersionUID = 1L;
    
    /**
     * Constructor to create an instance of InternalServerErrorException with a custom message.
     * 
     * @param msg The message to be associated with the exception.
     */
    public InternalServerErrorException(String msg) {
        super(msg);  // Pass the message to the parent Exception class
    }
}

