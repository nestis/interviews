package com.nestis.interview.tests.service;

import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Optional;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.amqp.rabbit.support.PendingConfirm;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.nestis.interview.tests.entity.Token;
import com.nestis.interview.tests.repository.TestRepository;
import com.nestis.interview.tests.repository.TokenRepository;
import com.nestis.interview.tests.service.impl.TestServiceImpl;
import com.nestis.interview.tests.service.model.FinishedTestDto;

@RunWith(SpringRunner.class)
public class TestServiceTest {

	private TestService testService;
	
	@MockBean
	private TestRepository testRepository;
	
	@MockBean
	private TokenRepository tokenRepository;
	
	@MockBean
	private RabbitTemplate rabbitTemplate;
	
	@Before
	public void setup() {
		this.testService = new TestServiceImpl(testRepository, tokenRepository, rabbitTemplate);
	}
	
	@Test
	public void shouldGetTest() throws Exception {
		com.nestis.interview.tests.entity.Test test = new com.nestis.interview.tests.entity.Test();
		test.setLeader("Test Leader");
		test.setName("Test candidate");
		test.setTestId(1);
		given(testRepository.findByTestId(anyInt())).willReturn(Optional.of(test));
		
		com.nestis.interview.tests.entity.Test testResponse = testService.getTestById(1);
		assertThat(testResponse.getTestId(), Matchers.comparesEqualTo(1));
	}
	
	@Test(expected = RuntimeException.class)
	public void shouldGetAnEmptyTest() throws Exception {
		given(testRepository.findByTestId(anyInt())).willReturn(Optional.empty());
		testService.getTestById(1);
	}
	
	@Test
	public void shouldCreateNewTest() throws Exception {

		com.nestis.interview.tests.entity.Test  lastTest = new com.nestis.interview.tests.entity.Test();
		lastTest.setTestId(1);
		this.testRepository.findFirstByOrderByTestIdDesc();
		given(testRepository.findFirstByOrderByTestIdDesc()).willReturn(lastTest);
		
		com.nestis.interview.tests.entity.Test test = new com.nestis.interview.tests.entity.Test();
		test.setLeader("Test Leader");
		test.setName("Test candidate");
		given(testRepository.save(any())).willReturn(test);
		
		Token token = new Token();
		token.setId("TESTOKEN");
		given(tokenRepository.save(any())).willReturn(token);
		
		String testToken = testService.createTest(test);
		assertThat(testToken, Matchers.notNullValue());
	}
	
	@Test
	public void shouldFinishTest() throws Exception {
		doCallRealMethod().when(rabbitTemplate).setConfirmCallback(any(RabbitTemplate.ConfirmCallback.class));
		this.testService = new TestServiceImpl(testRepository, tokenRepository, rabbitTemplate);
		
		
		com.nestis.interview.tests.entity.Test mockTest = new com.nestis.interview.tests.entity.Test();
		mockTest.setId("test");
		
		given(testRepository.findByTestId(1)).willReturn(Optional.of(mockTest));
		
		// Mock rabbitTemplate methods
		doNothing().when(rabbitTemplate).correlationConvertAndSend(any(FinishedTestDto.class), any(CorrelationData.class));
		
		// Call the confirmation callback
		PendingConfirm pendingConf = new PendingConfirm(new CorrelationData("1"), 1L);
		doCallRealMethod().when(rabbitTemplate).handleConfirm(pendingConf, true);
	
		FinishedTestDto finishTest = new FinishedTestDto();
		finishTest.setTestId(1);
		Boolean res = testService.finishTest(finishTest);

		rabbitTemplate.handleConfirm(pendingConf, true);
		
		verify(testRepository, times(1)).save(mockTest);
		assertThat(res, Matchers.equalTo(true));
	}
}
