package com.nestis.interview.tests.rest;

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
import com.nestis.interview.tests.entity.Token;
import com.nestis.interview.tests.service.TestService;
import com.nestis.interview.tests.service.TokenService;
import com.nestis.interview.tests.service.model.MarkTestDto;

import reactor.core.publisher.Mono;

/**
 * TestRestController
 * @author nestis
 *
 */
@RestController
@RequestMapping(value = "${endpoints.tests:tests}")
public class TestController {
	
	private TestService testService;
	
	private TokenService tokenService;
	
	/**
	 * Class constructor.
	 * @param testService TestService bean.
	 * @param tokenService TokenService bean.
	 */
	public TestController(@Autowired TestService testService, @Autowired TokenService tokenService) {
		this.testService = testService;
		this.tokenService = tokenService;
	}
	
	/**
	 * Gets the test for the given token.
	 * @param token Test token.
	 * @return Mono containing the test object.
	 */
	@GetMapping(value = "/{token}")
	public Mono<ResponseEntity<Test>> getTest(@PathVariable String token) {
		Token tokenInfo = this.tokenService.getToken(token);
		ResponseEntity<Test> response;
		if (tokenInfo == null) {
			response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			Test test = this.testService.getTestById(tokenInfo.getTestId());
			response = new ResponseEntity<Test>(test, HttpStatus.OK);
		}
		return Mono.just(response);
	}
	
	/**
	 * Creates a new test.
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
	public Mono<String> markTest(@PathVariable String token, @RequestBody MarkTestDto answers) {
		return null;
	}
}
