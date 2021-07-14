package com.cubic.api.exception;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.cubic.api.model.Error;
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	private static final String CONTENT_TYPE = "Content-Type";

	private static final String JSON = "application/json;charset=utf-8";

	@ExceptionHandler(RecordNotFoundException.class)
	protected ResponseEntity<Error> handleInvalidFields(WebRequest request, RecordNotFoundException ex) {
		final HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add(CONTENT_TYPE, JSON);
		Error error = new Error();
		error.setCode("32040004");
		error.setReason(ex.getMessage());

		return new ResponseEntity<Error>(error, HttpStatus.NOT_FOUND);
	}

	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Error> handleGeneralServerException(Exception ex) {

		Error error = new Error();
		error.setCode("32050000");
		error.setReason("General Exception - " + ex.getMessage());

		// Actual exception handling
		return new ResponseEntity<Error>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
