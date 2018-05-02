package com.nestis.interview.tests.config;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * RabbitMQ Producer configuration class.
 * RabbitTemplates used to publish into exchanges will be defined here.
 * @author nestis
 *
 */
@Configuration
public class RabbitProducerConfiguration {
	
	@Value("${config.rabbit.producer.question.exchange}")
	private String questionExchange;
	
	@Value("${config.rabbit.producer.question.routingKey}")
	private String questionRoutingKey;

	/**
	 * Creates a RabbitTemplate that publish a finished test into Questions Exchange.
	 * @param connectionFactory Rabbit Connection Factory.
	 * @return RabbitTemplate instance.
	 */
	@Bean
	public RabbitTemplate testFinishedRabbitTemplate(ConnectionFactory connectionFactory) {
		final RabbitTemplate rabbitTemplate =  new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
		rabbitTemplate.setExchange(questionExchange);
		rabbitTemplate.setRoutingKey(questionRoutingKey);
		
		return rabbitTemplate;
	}
}
