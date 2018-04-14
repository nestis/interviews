package com.nestis.interview.tests.service.impl;

import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nestis.interview.tests.entity.Test;
import com.nestis.interview.tests.entity.Token;
import com.nestis.interview.tests.repository.TestRepository;
import com.nestis.interview.tests.repository.TokenRepository;
import com.nestis.interview.tests.service.TestService;
import com.nestis.interview.tests.service.model.MarkTestDto;

@Service
public class TestServiceImpl implements TestService {
	
	private final Logger log = LoggerFactory.getLogger(TestServiceImpl.class);
	
	private TestRepository testRepository;
	
	private TokenRepository tokenRepository;
	
	@Autowired
	public TestServiceImpl(TestRepository testRepository, TokenRepository tokenRepository) {
		this.testRepository = testRepository;
		this.tokenRepository = tokenRepository;
	}
	
	@Override
	public Test getTestById(Integer testId) {
		Optional<Test> test = this.testRepository.findByTestId(testId);
		return test.orElseThrow(() -> new RuntimeException("Test not found"));
	}

	@Override
	@Transactional
	public String createTest(Test test) {
		// Generate the new test token
		String testToken = UUID.randomUUID().toString();
		
		try {
			// Get the lst test id
			Test testId = this.testRepository.findFirstByOrderByTestIdDesc();
			
			// Set the new testId and save the entity
			test.setTestId(testId.getTestId() + 1);
			Test savedTest = this.testRepository.save(test);
			
			// Save the new token entity
			Token token = new Token();
			token.setToken(testToken);
			token.setTestId(savedTest.getTestId());
			tokenRepository.save(token);
			
		} catch (DataAccessException ex) {
			log.error(ex.getMessage());
			throw new RuntimeException(ex);
		}
		
		return testToken;
	}

	@Override
	public boolean markTest(MarkTestDto test) {
		return false;
	}

}
