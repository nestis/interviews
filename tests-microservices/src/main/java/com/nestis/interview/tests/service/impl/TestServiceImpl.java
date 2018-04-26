 package com.nestis.interview.tests.service.impl;

import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nestis.interview.tests.entity.Test;
import com.nestis.interview.tests.entity.Token;
import com.nestis.interview.tests.exception.EntityNotFoundException;
import com.nestis.interview.tests.exception.TestsException;
import com.nestis.interview.tests.repository.TestRepository;
import com.nestis.interview.tests.repository.TokenRepository;
import com.nestis.interview.tests.service.TestService;
import com.nestis.interview.tests.service.model.FinishTestDto;

@Service
public class TestServiceImpl implements TestService {
	
	private final Logger log = LoggerFactory.getLogger(TestServiceImpl.class);
	
	private TestRepository testRepository;
	
	private TokenRepository tokenRepository;
	
	private RabbitTemplate rabbitTemplate;
	
	@Value("${config.rabbit.question.exchange}")
	private String questionExchange;
	
	@Value("${config.rabbit.question.routingKey}")
	private String questionRoutingKey;
	
	
	@Autowired
	public TestServiceImpl(TestRepository testRepository, TokenRepository tokenRepository, RabbitTemplate rabbitTemplate) {
		this.testRepository = testRepository;
		this.tokenRepository = tokenRepository;
		this.rabbitTemplate = rabbitTemplate;
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
			// Get the last test id
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
	public boolean finishTest(FinishTestDto test) {
		Optional<Test> testBd = this.testRepository.findByTestId(test.getTestId());
		if (testBd.isPresent()) {
			try {
				// Set a confirmation publishing callback
				rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
					if (ack) {
						Test testEntity = null;
						testEntity = testBd.get();
						testEntity.setFinished(true);
						testRepository.save(testEntity);
					} else {
						throw new TestsException("Error publishing into queue: " + cause);
					}
				});
				
				// Send to queue
				rabbitTemplate.convertAndSend(questionExchange, questionRoutingKey, test);
				
			} catch (AmqpException ex) {
				log.error("There has been an error sending the test answer to the rabbit queue");
				log.error(ex.getMessage());
				throw new TestsException("Error sending the results!");
			} catch (DataAccessException daoEx) {
				log.error("There has been an error saving the finished state for testId " + test.getTestId());
				log.error(daoEx.getMessage());
				throw new TestsException("Error sending the results!");
			}
			return true;
		} else {
			throw new EntityNotFoundException("Test id "  + test.getTestId() + " not found!");
		}
	}

}
