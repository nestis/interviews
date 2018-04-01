package com.nestis.interview.tests.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.nestis.interview.tests.repository.TestRepository;
import com.nestis.interview.tests.service.impl.TestServiceImpl;

@RunWith(SpringRunner.class)
public class TestServiceTest {

	private TestService testService;
	
	@MockBean
	private TestRepository testRepository;
	
	@Before
	public void setup() {
		this.testService = new TestServiceImpl(testRepository);
	}
	
	@Test
	public void shouldGetTest() throws Exception {
		assert(false);
	}
	
	@Test
	public void shouldCreateNewTest() throws Exception {
		assert(false);
	}
	
	@Test
	public void shouldMarkTest() throws Exception {
		assert(false);
	}
}
