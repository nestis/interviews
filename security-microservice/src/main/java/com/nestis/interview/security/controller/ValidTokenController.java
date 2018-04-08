package com.nestis.interview.security.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/validToken")
public class ValidTokenController {

	@GetMapping
	public ResponseEntity<Void> getValidToken() {
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
