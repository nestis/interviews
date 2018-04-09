package com.nestis.interview.security.exception;

import lombok.Data;

@Data
public class TokenException {

	private String message;
	
	private Integer status;
	
	public TokenException(String message, Integer status) {
		this.message = message;
	}
}
