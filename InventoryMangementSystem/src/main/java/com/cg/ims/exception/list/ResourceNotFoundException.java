package com.cg.ims.exception.list;

/**
 * Custom exception class to represent a resource not found scenario.
 * This class extends the built-in Exception class.
 */
public class ResourceNotFoundException extends Exception {

    // Serial version UID for serialization compatibility
    private static final long serialVersionUID = 1L;
    
    /**
     * Constructor to create an instance of ResourceNotFoundException with a custom message.
     * 
     * @param msg The message to be associated with the exception.
     */
    public ResourceNotFoundException(String msg) {
        super(msg);  // Pass the message to the parent Exception class
    }
}
