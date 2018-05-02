package com.nestis.interview.tests.service.impl;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nestis.interview.tests.entity.Test;
import com.nestis.interview.tests.exception.EntityNotFoundException;
import com.nestis.interview.tests.repository.TestRepository;
import com.nestis.interview.tests.service.TestMarkedService;
import com.nestis.interview.tests.service.model.MarkedTestDto;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TestMarkedServiceImpl implements TestMarkedService {

	private TestRepository testRepository;
	
	@Autowired
	public TestMarkedServiceImpl(TestRepository testRepository) {
		this.testRepository = testRepository;
	}
	
	@RabbitListener(queues = { "${config.rabbit.consumer.testMarked.queue}" })
	public void handleMarkedTest(MarkedTestDto test) {
		Test testEntity = testRepository.findByTestId(test.getTestId()).orElseThrow(() -> {
			log.error("No test found for received testId " + test.getTestId());
			throw new EntityNotFoundException("TestId " + test.getTestId() + " not found!");
		});
		testEntity.setScore(test.getScore());
		testRepository.save(testEntity);
		
		// TODO send email
	}

}
