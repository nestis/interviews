package com.nestis.interview.tests.exception;

import lombok.Getter;

@Getter
public class TestsException extends RuntimeException {
	
	private static final long serialVersionUID = 6666323529327251267L;

	public TestsException(String message) {
		super(message);
	}
}
