package com.nestis.interview.tests.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * RabbitMQ consumer configuration.
 * Defines the exchanges, queues and bindings to be used by test microservice.
 * @author nestis
 *
 */
@Configuration
public class RabbitConfiguration {

	@Value("${config.rabbit.consumer.exchange}")
	private String testExchange;

	@Value("${config.rabbit.consumer.testMarked.queue}")
	private String testQueue;

	@Value("${config.rabbit.consumer.testMarked.routingKey}")
	private String testMarkedRoutingKey;
	
	@Bean
	public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
		return new RabbitAdmin(connectionFactory);
	}
	
	@Bean
	public DirectExchange testsExchange() {
		return new DirectExchange(testExchange, true, false);
	}
	
	@Bean
	public Queue testMarkedQueue() {
		return new Queue(testQueue, true);
	}

	@Bean
	public Binding testMarkedBinding(Queue testMarkedQueue, DirectExchange testExchange) {
		return BindingBuilder.bind(testMarkedQueue).to(testExchange).with(testMarkedRoutingKey);
	}

	@Bean
	public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
		final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
		return rabbitTemplate;
	}

	@Bean
	public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
		return new Jackson2JsonMessageConverter();
	}
}
