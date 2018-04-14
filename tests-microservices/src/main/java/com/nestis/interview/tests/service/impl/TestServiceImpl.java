package com.nestis.interview.tests.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nestis.interview.tests.entity.Test;
import com.nestis.interview.tests.repository.TestRepository;
import com.nestis.interview.tests.service.TestService;
import com.nestis.interview.tests.service.model.MarkTestDto;

@Service
public class TestServiceImpl implements TestService {
	
	private TestRepository testRepository;
	
	public TestServiceImpl(@Autowired TestRepository testRepository) {
		this.testRepository = testRepository;
	}
	
	@Override
	public Test getTestById(Integer testId) {
		Test test = this.testRepository.findByTestId(testId);
		return test;
	}

	@Override
	public String createTest(Test test) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean markTest(MarkTestDto test) {
		// TODO Auto-generated method stub
		return false;
		
	}

}
