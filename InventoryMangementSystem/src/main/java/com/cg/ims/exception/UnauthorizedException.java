package com.cg.ims.exception;

/**
 * Custom exception class to represent an unauthorized access scenario.
 * This class extends the built-in Exception class.
 */
public class UnauthorizedException extends Exception {

    // Serial version UID for serialization compatibility
    private static final long serialVersionUID = 1L;
    
    /**
     * Constructor to create an instance of UnauthorizedException with a custom message.
     * 
     * @param msg The message to be associated with the exception.
     */
    public UnauthorizedException(String msg) {
        super(msg);  // Pass the message to the parent Exception class
    }
}

