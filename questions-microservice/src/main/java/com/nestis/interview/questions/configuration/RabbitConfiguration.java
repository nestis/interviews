package com.nestis.interview.questions.configuration;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * RabbitMQ Configuration class. Inits the rabbit mq queues and exchanges if
 * they havent been init before.
 * 
 * @author nestis
 *
 */
@Configuration
public class RabbitConfiguration {

	@Value("${config.rabbit.question.testFinished.queue}")
	private String testQueue;

	@Value("${config.rabbit.question.exchange}")
	private String questionExchange;

	@Value("${config.rabbit.question.testFinished.routingKey}")
	private String testFinishRoutingKey;

	/**
	 * Declare RabbitAdmin bean so the queues, exchanges and bindings are
	 * automatically created.
	 * 
	 * @param connFactory
	 * @return RabbitAdmin.
	 */
	@Bean
	public AmqpAdmin amqpAdmin(ConnectionFactory connFactory) {
		return new RabbitAdmin(connFactory);
	}

	@Bean
	public Queue questionQueue() {
		return new Queue(testQueue, true);
	}

	@Bean
	public DirectExchange questionExchange() {
		return new DirectExchange(questionExchange, true, true);
	}

	@Bean
	public Binding questionBinding(Queue questionQueue, DirectExchange questionExchange) {
		return BindingBuilder.bind(questionQueue).to(questionExchange).with(testFinishRoutingKey);
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
