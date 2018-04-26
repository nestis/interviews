package com.nestis.interview.tests.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Rest Exception handler
 * @author nestis
 *
 */
@RestControllerAdvice("com.nestis.interview.tests.rest")
public class TestsControllerAdvice {

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<TestExceptionResponse> handleEntityNotFoundException(EntityNotFoundException ex) {
		TestExceptionResponse testException = new TestExceptionResponse(ex.getMessage());
		return new ResponseEntity<TestExceptionResponse>(testException, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<TestExceptionResponse> handleException(Exception ex) {
		TestExceptionResponse testException = new TestExceptionResponse(ex.getMessage());
		return new ResponseEntity<TestExceptionResponse>(testException, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
