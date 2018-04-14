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

	@ExceptionHandler(Exception.class)
	public ResponseEntity<TestsException> handleException(Exception ex) {
		TestsException testException = new TestsException();
		testException.setMessage(ex.getMessage());
		return new ResponseEntity<TestsException>(testException, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
