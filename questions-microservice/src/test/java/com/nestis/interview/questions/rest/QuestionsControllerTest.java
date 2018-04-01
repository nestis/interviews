package com.nestis.interview.questions.rest;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import com.nestis.interview.questions.QuestionsApplication;
import com.nestis.interview.questions.entity.Question;
import com.nestis.interview.questions.rest.model.AnswerDto;
import com.nestis.interview.questions.rest.model.QuestionDto;
import com.nestis.interview.questions.service.QuestionService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = QuestionsApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = "endpoints.questions=/questions")
public class QuestionsControllerTest {

    @Autowired
    private WebTestClient webTestClient;
    
	@MockBean
	private QuestionService questionService;
	
	@Test
	public void shouldGetQuestions() throws Exception {
		List<Question> response = new ArrayList<>();
		Question q = new Question();
		q.setId("TEST");
		response.add(q);
		
		given(questionService.getQuestions(anyList())).willReturn(response);
		
		List<Integer> ids = new ArrayList<Integer>(0);
		webTestClient.post().uri("/questions")
			.accept(MediaType.APPLICATION_JSON)
			.body(BodyInserters.fromObject(ids))
			.exchange()
			.expectBodyList(Question.class)
			.hasSize(1);
	}
	
	@Test
	public void shouldPostNewQuestions() throws Exception {
		List<QuestionDto> qIn = new ArrayList<>();
		QuestionDto q = new QuestionDto();
		q.setQuestionId(1);
		q.setCategoryId(2);
		q.setText("Test question?");
		AnswerDto a = new AnswerDto();
		a.setId(1);
		a.setText("Test!");
		List<AnswerDto> answers = new ArrayList<AnswerDto>();
		answers.add(a);
		q.setAnswers(answers);
		qIn.add(q);
		
		given(questionService.saveQuestions(anyList())).willReturn(true);
		
		webTestClient.post().uri("/questions/add")
			.accept(MediaType.APPLICATION_JSON)
			.body(BodyInserters.fromObject(qIn))
			.exchange()
			.expectBody().equals(true);
	}
}
