package com.cg.ims.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

/**
 * A centralized exception handler for the entire application.
 * This class uses @ControllerAdvice to intercept exceptions and return appropriate HTTP responses.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles validation exceptions triggered by @Valid or validation annotations.
     * Extracts field-specific errors and returns them in a key-value format.
     *
     * @param ex the MethodArgumentNotValidException exception.
     * @return a ResponseEntity containing field errors and a BAD_REQUEST (400) status.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles exceptions related to invalid data (e.g., InvalidDataException).
     *
     * @param ex the InvalidDataException.
     * @param request the WebRequest containing request details.
     * @return a ResponseEntity containing error details and a BAD_REQUEST (400) status.
     */
    @ExceptionHandler(InvalidDataException.class)
    public ResponseEntity<?> handleInvalidProductDataException(InvalidDataException ex, WebRequest request) {
        ErrorMapper errorDetails = new ErrorMapper(HttpStatus.BAD_REQUEST, ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles internal server errors (e.g., InternalServerErrorException).
     *
     * @param ex the InternalServerErrorException.
     * @param request the WebRequest containing request details.
     * @return a ResponseEntity with the exception message and an INTERNAL_SERVER_ERROR (500) status.
     */
    @ExceptionHandler(value = InternalServerErrorException.class)
    public ResponseEntity<?> handleException(InternalServerErrorException ex, WebRequest request) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Handles exceptions when a requested resource is not found (e.g., ResourceNotFoundException).
     *
     * @param ex the ResourceNotFoundException.
     * @param request the WebRequest containing request details.
     * @return a ResponseEntity containing error details and a NOT_FOUND (404) status.
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        ErrorMapper errorDetails = new ErrorMapper(HttpStatus.NOT_FOUND, ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    /**
     * Handles unauthorized access exceptions (e.g., UnauthorizedException).
     *
     * @param ex the UnauthorizedException.
     * @param request the WebRequest containing request details.
     * @return a ResponseEntity containing error details and a NOT_FOUND (404) status.
     */
    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<?> handleUnauthorizedException(UnauthorizedException ex, WebRequest request) {
        ErrorMapper errorDetails = new ErrorMapper(HttpStatus.NOT_FOUND, ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    /**
     * Handles bad request exceptions (e.g., BadRequestException).
     *
     * @param ex the BadRequestException.
     * @param request the WebRequest containing request details.
     * @return a ResponseEntity containing error details and a BAD_REQUEST (400) status.
     */
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<?> handleBadRequestException(BadRequestException ex, WebRequest request) {
        ErrorMapper errorDetails = new ErrorMapper(HttpStatus.BAD_REQUEST, ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
}

