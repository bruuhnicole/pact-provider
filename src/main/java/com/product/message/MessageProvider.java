package com.product.message;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Takes a {@link ProductCreatedMessage}, converts it to a {@link String} and sends it to be published.
 */
class MessageProvider {

	private Logger logger = LoggerFactory.getLogger(MessageProvider.class);

	private ObjectMapper objectMapper;

	private MessagePublisher messagePublisher;

	MessageProvider(ObjectMapper objectMapper, MessagePublisher messagePublisher) {
		this.objectMapper = objectMapper;
		this.messagePublisher = messagePublisher;
	}

	void providerProductCreatedMessage(ProductCreatedMessage message) throws IOException {
		String stringMessage = objectMapper.writeValueAsString(message);
		messagePublisher.publishMessage(stringMessage, "product.created");
		logger.info("Published message '{}'", stringMessage);
	}

}
