package com.cg.ims.exception.list;

/**
 * Custom exception class to represent an invalid data scenario.
 * This class extends the built-in Exception class.
 */
public class InvalidDataException extends Exception {

    // Serial version UID for serialization compatibility
    private static final long serialVersionUID = 1L;
    
    /**
     * Constructor to create an instance of InvalidDataException with a custom message.
     * 
     * @param msg The message to be associated with the exception.
     */
    public InvalidDataException(String msg) {
        super(msg);  // Pass the message to the parent Exception class
    }
}
