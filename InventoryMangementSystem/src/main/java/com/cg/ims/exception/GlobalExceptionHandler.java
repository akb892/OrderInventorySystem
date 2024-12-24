package com.cg.ims.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.cg.ims.exception.list.BadRequestException;
import com.cg.ims.exception.list.InternalServerErrorException;
import com.cg.ims.exception.list.InvalidDataException;
import com.cg.ims.exception.list.ResourceNotFoundException;
import com.cg.ims.exception.list.UnauthorizedException;

@ControllerAdvice
public class GlobalExceptionHandler {


	// Handle validation exceptions
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getFieldErrors()
				.forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	}
	
	
	// Handle InvalidProductDataException
		@ExceptionHandler(InvalidDataException.class)
		public ResponseEntity<?> handleInvalidProductDataException(InvalidDataException ex, WebRequest request) {
			ErrorMapper errorDetails = new ErrorMapper(HttpStatus.BAD_REQUEST, ex.getMessage(),
					request.getDescription(false));
			return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
		}

		// Handle InternalServerErrorException
		@ExceptionHandler(value = InternalServerErrorException.class)
		public ResponseEntity<?> handleException(InternalServerErrorException ex, WebRequest request) {
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

		// Handle NotFoundException
		@ExceptionHandler(ResourceNotFoundException.class)
		public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
			ErrorMapper errorDetails = new ErrorMapper(HttpStatus.NOT_FOUND, ex.getMessage(),
					request.getDescription(false));
			return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
		}

		// Handle NotFoundException
		@ExceptionHandler(UnauthorizedException.class)
		public ResponseEntity<?> handleUnauthorizedException(UnauthorizedException ex, WebRequest request) {
			ErrorMapper errorDetails = new ErrorMapper(HttpStatus.NOT_FOUND, ex.getMessage(),
					request.getDescription(false));
			return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
		}

		// Handle Exception
		@ExceptionHandler(BadRequestException.class)
		public ResponseEntity<?> handleBadRequestException(BadRequestException ex, WebRequest request) {
			ErrorMapper errorDetails = new ErrorMapper(HttpStatus.BAD_REQUEST, ex.getMessage(),
					request.getDescription(false));
			return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
		}
}
