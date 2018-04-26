package com.nestis.interview.tests.exception;

import lombok.Getter;

@Getter
public class EntityNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -3722587130574177118L;

	public EntityNotFoundException(String message) {
		super(message);
	}
}
