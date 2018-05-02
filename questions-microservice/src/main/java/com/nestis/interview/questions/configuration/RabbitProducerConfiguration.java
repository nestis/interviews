package com.nestis.interview.questions.configuration;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitProducerConfiguration {

	@Value("${config.rabbit.producer.test.exchange}")
	private String testExchange;
	
	@Value("${config.rabbit.producer.test.routingKey}")
	private String testMarkedRoutingKey;
	
	@Bean
	public RabbitTemplate testMarkedRabbitTemplate(ConnectionFactory connectionFactory) {
		RabbitTemplate template = new RabbitTemplate(connectionFactory);
		template.setExchange(testExchange);
		template.setRoutingKey(testMarkedRoutingKey);
		return template;
	}
}
