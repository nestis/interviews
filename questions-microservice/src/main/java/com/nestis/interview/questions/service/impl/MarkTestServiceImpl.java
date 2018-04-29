package com.nestis.interview.questions.service.impl;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.nestis.interview.questions.service.MarkTestService;
import com.nestis.interview.questions.service.model.MarkTestDto;

@Service
public class MarkTestServiceImpl implements MarkTestService {

	@Override
	@RabbitListener(queues = "${config.rabbit.question.testFinished.queue}")
	public void markTest(MarkTestDto markTest) {
		System.out.println(markTest);
	}

}
