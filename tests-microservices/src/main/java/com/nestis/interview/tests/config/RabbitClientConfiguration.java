package com.nestis.interview.tests.config;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitClientConfiguration {
	
	@Value("${config.rabbit.question.exchange}")
	private String questionExchange;
	
	@Value("${config.rabbit.question.routingKey}")
	private String questionRoutingKey;

	@Bean
	@Qualifier("testFinishedRabbitTemplate")
	public RabbitTemplate testFinishedRabbitTemplate(ConnectionFactory connectionFactory) {
		final RabbitTemplate rabbitTemplate =  new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
		rabbitTemplate.setExchange(questionExchange);
		rabbitTemplate.setRoutingKey(questionRoutingKey);
		
		return rabbitTemplate;
	}
}
