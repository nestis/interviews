package com.nestis.interview.questions.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nestis.interview.questions.entity.Question;
import com.nestis.interview.questions.repository.QuestionRepository;
import com.nestis.interview.questions.service.MarkTestService;
import com.nestis.interview.questions.service.model.MarkTestDto;
import com.nestis.interview.questions.service.model.MarkedTestDto;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MarkTestServiceImpl implements MarkTestService {

	private QuestionRepository questionRepository;
	
	private RabbitTemplate rabbitTemplate;
	
	@Autowired
	public MarkTestServiceImpl(QuestionRepository questionRepository, RabbitTemplate rabbitTemplate) {
		this.questionRepository = questionRepository;
		this.rabbitTemplate = rabbitTemplate;
	}
	
	@Override
	@RabbitListener(queues = "${config.rabbit.consumer.testFinished.queue}")
	public void markTest(MarkTestDto markTest) {
		Integer[] qs = markTest.getAnswers().keySet().toArray(new Integer[0]);
		List<Integer> questionIds = new ArrayList<Integer>(Arrays.asList(qs));
		
		List<Question> questions = questionRepository.findByQuestionIdIn(questionIds);
		
		Integer correct = questions.stream().filter(q -> {
			String answers = markTest.getAnswers().get(q.getQuestionId());
			if (answers.equals(q.getCorrect())) {
				return true;
			}
			return false;
		}).toArray().length;
		
		Float percent = ((float)correct / (float)questions.size()) * 100;
		log.info("TestId %1 result %2%", markTest.getTestId(), percent);
		
		MarkedTestDto markedTest = new MarkedTestDto();
		markedTest.setScore(percent);
		markedTest.setTestId(markTest.getTestId());
		rabbitTemplate.convertAndSend(markedTest);
	}

}
