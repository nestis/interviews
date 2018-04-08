package com.nestis.interview.tests.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nestis.interview.tests.entity.Test;
import com.nestis.interview.tests.service.TestService;

import reactor.core.publisher.Mono;

/**
 * TestRestController
 * @author nestis
 *
 */
@RestController
@RequestMapping(value = "${endpoints.tests:/test}")
public class TestController {
	
	private TestService testService;
	
	public TestController(@Autowired TestService testService) {
		this.testService = testService;
	}
	
	/**
	 * Get the test for the given token.
	 * @param token Test token.
	 * @return Mono containing the test object.
	 */
	@GetMapping(value = "/{token}")
	public Mono<ResponseEntity<Test>> getTest(@PathVariable String token) {
		Test test = this.testService.getTestByToken(token);
		ResponseEntity<Test> response;
		if (test == null) {
			response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			response = new ResponseEntity<Test>(test, HttpStatus.OK);
		}
		return Mono.just(response);
	}
	
	/**
	 * Create a new test.
	 * @param test Test object to save.
	 * @return String containing the token for the new test.
	 */
	@PostMapping
	public Mono<String> newTest(@RequestBody Test test) {
		return null;
	}
	
	/**
	 * Marks a test.
	 * @param token Test token.
	 * @param answers List of answers.
	 * @return String
	 */
	@PostMapping(value = "/{token}/mark")
	public Mono<String> markTest(@PathVariable String token, @RequestBody List<Integer> answers) {
		return null;
	}
}
