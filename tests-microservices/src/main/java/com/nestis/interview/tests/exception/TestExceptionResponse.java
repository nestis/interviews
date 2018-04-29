package com.nestis.interview.tests.exception;

import lombok.Getter;

@Getter
public class TestExceptionResponse {
	
	private String message;
	
	public TestExceptionResponse(String message) {
		this.message = message;
	}
}
