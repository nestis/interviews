package com.nestis.interview.security.controller;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.nestis.interview.security.exception.TokenException;

@RestControllerAdvice
public class TokenControllerAdvice {

	private final Logger log = LoggerFactory.getLogger(TokenControllerAdvice.class);
	  
	@ExceptionHandler(Exception.class)
	public ResponseEntity<TokenException> handleException(HttpServletResponse response, Exception ex) {
		log.error(ex.getMessage());
		TokenException tokenException = new TokenException(ex.getLocalizedMessage(), response.getStatus());
		return new ResponseEntity<TokenException>(tokenException, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
