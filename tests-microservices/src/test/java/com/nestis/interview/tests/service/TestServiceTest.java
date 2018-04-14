package com.nestis.interview.tests.service;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.*;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.nestis.interview.tests.entity.Token;
import com.nestis.interview.tests.repository.TestRepository;
import com.nestis.interview.tests.repository.TokenRepository;
import com.nestis.interview.tests.service.impl.TestServiceImpl;

@RunWith(SpringRunner.class)
public class TestServiceTest {

	private TestService testService;
	
	@MockBean
	private TestRepository testRepository;
	
	@MockBean
	private TokenRepository tokenRepository;
	
	@Before
	public void setup() {
		this.testService = new TestServiceImpl(testRepository, tokenRepository);
	}
	
	@Test
	public void shouldGetTest() throws Exception {
		com.nestis.interview.tests.entity.Test test = new com.nestis.interview.tests.entity.Test();
		test.setLeader("Test Leader");
		test.setName("Test candidate");
		given(testRepository.findByTestId(anyInt())).willReturn(Optional.of(test));
		
		com.nestis.interview.tests.entity.Test testResponse = testService.getTestById(1);
		assertThat(testResponse.getTestId(), comparesEqualTo(1));
	}
	
	@Test(expected = RuntimeException.class)
	public void shouldGetAnEmptyTest() throws Exception {
		given(testRepository.findByTestId(anyInt())).willReturn(Optional.empty());
		testService.getTestById(1);
	}
	
	@Test
	public void shouldCreateNewTest() throws Exception {
		com.nestis.interview.tests.entity.Test test = new com.nestis.interview.tests.entity.Test();
		test.setLeader("Test Leader");
		test.setName("Test candidate");
		given(testRepository.save(any())).willReturn(test);
		
		Token token = new Token();
		token.setId("TESTOKEN");
		given(tokenRepository.save(any())).willReturn(token);
		
		String testToken = testService.createTest(test);
		assertThat(testToken, notNullValue());
	}
	
	@Test
	public void shouldMarkTest() throws Exception {
		assert(false);
	}
}
