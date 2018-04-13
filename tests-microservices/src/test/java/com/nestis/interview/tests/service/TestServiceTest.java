package com.nestis.interview.tests.service;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.*;

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
		com.nestis.interview.tests.entity.Test test = new com.nestis.interview.tests.entity.Test();
		test.setLeader("Test Leader");
		test.setName("Test candidate");
		given(testRepository.findByTestId(anyInt())).willReturn(test);
		
		com.nestis.interview.tests.entity.Test testResponse = testService.getTestById(1);
		assertThat(testResponse.getTestId(), comparesEqualTo(1));
	}
	
	@Test
	public void shouldCreateNewTest() throws Exception {
		com.nestis.interview.tests.entity.Test test = new com.nestis.interview.tests.entity.Test();
		test.setLeader("Test Leader");
		test.setName("Test candidate");
		given(testService.createTest(any())).willReturn("TESTTOKEN");
		
		String testToken = testService.createTest(test);
		assertThat(testToken, comparesEqualTo("TESTTOKEN"));
	}
	
	@Test
	public void shouldMarkTest() throws Exception {
		assert(false);
	}
}
