package com.bookify.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleMethodArgumentNotValidException(
			MethodArgumentNotValidException ex) {

		Map<String, String> errorMap = new HashMap<>();

		ex.getBindingResult().getFieldErrors().forEach(err -> {

			String fieldName = err.getField();

			String message = err.getDefaultMessage();

			errorMap.put(fieldName, message);

		});

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMap);
	}

	@ExceptionHandler(AlreadyExistException.class)
	public ResponseEntity<ErrorResponse> handleAlreadyExistException(AlreadyExistException ex, WebRequest request) {

		String requestURI = request.getDescription(false);

		ErrorResponse error = ErrorResponse.builder()
				.statusCode(HttpStatus.CONFLICT.value())
				.message(ex.getMessage())
				.time(LocalDateTime.now())
				.path(requestURI.substring(4))
				.build();

		return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex,
			WebRequest request) {

		String requestURI = request.getDescription(false);

		ErrorResponse error = ErrorResponse.builder()
				.statusCode(HttpStatus.NOT_FOUND.value())
				.message(ex.getMessage())
				.time(LocalDateTime.now())
				.path(requestURI.substring(4))
				.build();

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException ex,
			WebRequest request) {

		String requestURI = request.getDescription(false);

		ErrorResponse error = ErrorResponse.builder()
				.statusCode(HttpStatus.BAD_REQUEST.value())
				.message(ex.getMessage())
				.time(LocalDateTime.now())
				.path(requestURI.substring(4))
				.build();

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}

	@ExceptionHandler(value = MyApplicationException.class)
	public ResponseEntity<ErrorResponse> handleMyApplicationException(MyApplicationException ex, WebRequest request) {

		String requestURI = request.getDescription(false);

		ErrorResponse error = ErrorResponse.builder()
				.statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
				.message("An unexpected error occurred: " + ex.getMessage())
				.time(LocalDateTime.now())
				.path(requestURI.substring(4))
				.build();

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
	}

}
