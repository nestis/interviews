 package com.nestis.interview.tests.service.impl;

import java.util.Optional;
import java.util.UUID;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.nestis.interview.tests.entity.Test;
import com.nestis.interview.tests.entity.Token;
import com.nestis.interview.tests.exception.EntityNotFoundException;
import com.nestis.interview.tests.exception.TestsException;
import com.nestis.interview.tests.repository.TestRepository;
import com.nestis.interview.tests.repository.TokenRepository;
import com.nestis.interview.tests.service.TestService;
import com.nestis.interview.tests.service.model.FinishedTestDto;

import lombok.extern.slf4j.Slf4j;

/**
 * TestService interface implementation.
 * @author nestis
 *
 */
@Service
@Slf4j
public class TestServiceImpl implements TestService {
	
	private TestRepository testRepository;
	
	private TokenRepository tokenRepository;
	
	private RabbitTemplate rabbitTemplate;
	
	@Autowired
	public TestServiceImpl(TestRepository testRepository, TokenRepository tokenRepository,
			@Qualifier("testFinishedRabbitTemplate") RabbitTemplate rabbitTemplate) {
		this.testRepository = testRepository;
		this.tokenRepository = tokenRepository;
		this.rabbitTemplate = rabbitTemplate;
		setRabbitCallback();
	}
	
	@Override
	public Test getTestById(Integer testId) {
		Optional<Test> test = this.testRepository.findByTestId(testId);
		return test.orElseThrow(() -> new RuntimeException("Test not found"));
	}

	@Override
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
	public boolean finishTest(FinishedTestDto test) {
		Optional<Test> testBd = this.testRepository.findByTestId(test.getTestId());
		if (testBd.isPresent()) {
			try {
				// Send the test results to rabbit.
				// The confirmCallback will update the test entity in mongodb.
				rabbitTemplate.correlationConvertAndSend(test, new CorrelationData(test.getTestId().toString()));
				
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
	
	/**
	 * Sets the rabbitTemplate callback.
	 * It will update the published test, to set its propoerty finished to true.
	 * The testId is expected to come as the correlation data.
	 */
	private void setRabbitCallback() {
		// Set a confirmation publishing callback
		this.rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
			if (ack) {
				Integer testId = Integer.parseInt(correlationData.getId());
				Optional<Test> testOpt = this.testRepository.findByTestId(testId);
				Test testEntity = testOpt.get();
				testEntity.setFinished(true);
				testRepository.save(testEntity);
			} else {
				log.error("There has been an error publishing into queue: " + cause);
				throw new TestsException("Error publishing into queue: " + cause);
			}
		});
	}

}
