package com.product.message;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
class MessageProviderConfiguration {

	@Bean
	TopicExchange topicExchange() {
		return new TopicExchange("myExchange");
	}


	@Bean
	MessageProvider messageProvider(ObjectMapper objectMapper, MessagePublisher publisher) {
		return new MessageProvider(objectMapper, publisher);
	}

	@Bean
	MessagePublisher messagePublisher(RabbitTemplate rabbitTemplate, TopicExchange topicExchange) {
		return new MessagePublisher(rabbitTemplate, topicExchange);
	}

	@Bean
	SendMessageJob job(MessageProvider messageProvider) {
		return new SendMessageJob(messageProvider);
	}


}
