package com.nestis.interview.questions.service;

import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.nestis.interview.questions.entity.Question;
import com.nestis.interview.questions.repository.QuestionRepository;
import com.nestis.interview.questions.service.impl.MarkTestServiceImpl;
import com.nestis.interview.questions.service.model.MarkTestDto;
import com.nestis.interview.questions.service.model.MarkedTestDto;

@RunWith(SpringRunner.class)
public class MarkTestServiceTest {

	private MarkTestServiceImpl markTestService;
	
	@Captor
	private ArgumentCaptor<MarkedTestDto> markedTestCaptor;
	
	@MockBean
	private QuestionRepository questionRepository;
	
	@MockBean
	private RabbitTemplate rabbitTemplate;
	
	@Before
	public void setup() {
		markTestService = new MarkTestServiceImpl(questionRepository, rabbitTemplate);
	}
	
	@Test
	public void shouldMarkATest() {
		MarkTestDto markTest = new MarkTestDto();
		Map<Integer, String> answers = new HashMap<Integer, String>(0);
		answers.put(1, "1");
		answers.put(2, "2");
		answers.put(3, "3");
		answers.put(4, "4,5");
		
		markTest.setTestId(1);
		markTest.setAnswers(answers);
		
		List<Question> questions = new ArrayList<Question>(0);
		Question q1 = new Question();
		q1.setQuestionId(1);
		q1.setCorrect("1");
		Question q2 = new Question();
		q2.setQuestionId(2);
		q2.setCorrect("1");
		Question q3 = new Question();
		q3.setQuestionId(3);
		q3.setCorrect("1");
		Question q4 = new Question();
		q4.setQuestionId(4);
		q4.setCorrect("4,5");
		questions.add(q1);
		questions.add(q2);
		questions.add(q3);
		questions.add(q4);
	
		given(questionRepository.findByQuestionIdIn(anyList())).willReturn(questions);
		doNothing().when(rabbitTemplate).convertAndSend(any());
		
		markTestService.markTest(markTest);
		
		verify(rabbitTemplate, times(1)).convertAndSend(markedTestCaptor.capture());
		assertThat(markedTestCaptor.getValue().getTestId(), Matchers.equalTo(1));
		assertThat(markedTestCaptor.getValue().getScore(), Matchers.equalTo(50f));
	}
}
