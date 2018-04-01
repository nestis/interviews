package com.nestis.interview.tests.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nestis.interview.tests.entity.Test;
import com.nestis.interview.tests.repository.TestRepository;
import com.nestis.interview.tests.service.TestService;

@Service
public class TestServiceImpl implements TestService {
	
	private TestRepository testRepository;
	
	public TestServiceImpl(@Autowired TestRepository testRepository) {
		this.testRepository = testRepository;
	}

	@Override
	public Test getTest(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String createTest(Test test) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void markTest(Test test) {
		// TODO Auto-generated method stub
		
	}

}
