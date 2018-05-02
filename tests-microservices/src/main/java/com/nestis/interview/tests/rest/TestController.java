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
import com.nestis.interview.tests.service.model.FinishedTestDto;

import reactor.core.publisher.Mono;

/**
 * TestRestController
 * @author nestis
 *
 */
@RestController
@RequestMapping(value = "${config.endpoints.tests:tests}")
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
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			Test test = this.testService.getTestById(tokenInfo.getTestId());
			response = new ResponseEntity<Test>(test, HttpStatus.OK);
		}
		return Mono.just(response);
	}
	
	/**
	 * Creates a new test.
	 * @param test Test object to save.
	 * @return Empty mono.
	 */
	@PostMapping
	public Mono<Void> newTest(@RequestBody Test test) {
		testService.createTest(test);
		return Mono.empty();
	}
	
	/**
	 * Finish a test.
	 * @param token Test token.
	 * @param answers List of answers.
	 * @return String
	 */
	@PostMapping(value = "/{token}")
	public Mono<Boolean> markTest(@PathVariable String token, @RequestBody FinishedTestDto answers) {
		return Mono.just(testService.finishTest(answers));
	}
}
