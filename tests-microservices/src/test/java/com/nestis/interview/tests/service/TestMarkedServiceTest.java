package com.nestis.interview.tests.service;


import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Optional;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.nestis.interview.tests.exception.EntityNotFoundException;
import com.nestis.interview.tests.repository.TestRepository;
import com.nestis.interview.tests.service.impl.TestMarkedServiceImpl;
import com.nestis.interview.tests.service.model.MarkedTestDto;

@RunWith(SpringRunner.class)
public class TestMarkedServiceTest {

	private TestMarkedServiceImpl testMarkedService;
	
	@MockBean
	private TestRepository testRepository;
	
	@Before
	public void setup() {
		testMarkedService = new TestMarkedServiceImpl(testRepository);
	}
	
	@Test
	public void shouldGetAScoreAndUpdateBD() {
		com.nestis.interview.tests.entity.Test mockTest = new com.nestis.interview.tests.entity.Test();
		mockTest.setTestId(1);
		Optional<com.nestis.interview.tests.entity.Test> optTest = Optional.of(mockTest);
		
		given(testRepository.findByTestId(anyInt())).willReturn(optTest);
		when(testRepository.save(any())).thenReturn(mockTest);
		
		MarkedTestDto markedTest = new MarkedTestDto();
		markedTest.setScore(90f);
		markedTest.setTestId(1);
		
		testMarkedService.handleMarkedTest(markedTest);
		assertThat(mockTest.getScore(), Matchers.equalTo(90f));
	
		verify(testRepository, times(1)).save(mockTest);
	}
	
	@Test(expected = EntityNotFoundException.class)
	public void shouldThrowAnException() {
		Optional<com.nestis.interview.tests.entity.Test> optTest = Optional.empty();
		
		given(testRepository.findByTestId(anyInt())).willReturn(optTest);
		
		MarkedTestDto markedTest = new MarkedTestDto();
		markedTest.setScore(90f);
		markedTest.setTestId(1);
		
		testMarkedService.handleMarkedTest(markedTest);
	}
}
