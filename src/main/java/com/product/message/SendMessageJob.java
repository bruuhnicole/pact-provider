package com.product.message;

import org.springframework.scheduling.annotation.Scheduled;

import java.io.IOException;
import java.util.Random;
import java.util.UUID;

class SendMessageJob {

	private Random random = new Random();

	private MessageProvider messageProvider;

	SendMessageJob(MessageProvider messageProvider) {
		this.messageProvider = messageProvider;
	}

	/**
	 * This scheduled job simulates the "real" business logic that should produce messages.
	 */
	@Scheduled(fixedDelay = 1000)
	void sendProductCreatedMessage() {
		try {
			ProductCreatedMessage productCreatedMessage = ProductCreatedMessage.builder()
							.messageUuid(UUID.randomUUID().toString())
							.product(Product.builder()
											.id(random.nextLong())
											.name("Batata")
											.build())
							.build();
			messageProvider.providerProductCreatedMessage(productCreatedMessage);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
