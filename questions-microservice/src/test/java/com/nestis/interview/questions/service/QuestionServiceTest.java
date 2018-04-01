package com.nestis.interview.questions.service;


import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.*;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.core.IsNull;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.nestis.interview.questions.entity.Question;
import com.nestis.interview.questions.repository.QuestionRepository;
import com.nestis.interview.questions.service.impl.QuestionServiceImpl;

@RunWith(SpringRunner.class)
public class QuestionServiceTest {

	private QuestionService questionService;
	
	@MockBean
	private QuestionRepository questionRepository;
	
	@Before
	public void setup() {
		questionService = new QuestionServiceImpl(questionRepository);
	}
	
	@Test
	public void shouldGetQuestions() throws Exception {
		List<Question> questions = new ArrayList<>();
		Question q1 = new Question();
		q1.setCategoryId(2);
		q1.setId("1");
		q1.setText("Test question");
		questions.add(q1);
		
		given(questionRepository.findByQuestionIdIn(anyList())).willReturn(questions);
		
		List<Question> response = questionService.getQuestions(new ArrayList<Integer>());

		assertThat(response, is(IsNull.notNullValue()));
		assertTrue(response.size() == 1);
	}
}
