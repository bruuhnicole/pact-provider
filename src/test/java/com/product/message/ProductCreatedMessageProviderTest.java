package com.product.message;

import au.com.dius.pact.provider.PactVerifyProvider;
import au.com.dius.pact.provider.junit.PactRunner;
import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.loader.PactFolder;
import au.com.dius.pact.provider.junit.target.Target;
import au.com.dius.pact.provider.junit.target.TestTarget;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.product.message.util.CustomAmqpTarget;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.Collections;
import java.util.UUID;

import static org.mockito.Mockito.*;

@RunWith(PactRunner.class)
@Provider("productservice")
@PactFolder("../pact-consumer/target/pacts")
public class ProductCreatedMessageProviderTest {

	@TestTarget
	public final Target target = new CustomAmqpTarget(Collections.singletonList("com.product.message"));

	private MessagePublisher publisher = Mockito.mock(MessagePublisher.class);

	private MessageProvider messageProvider = new MessageProvider(new ObjectMapper(), publisher);

	@PactVerifyProvider("a product created message")
	public String verifyProductCreatedMessage() throws IOException {
		// given
		doNothing().when(publisher).publishMessage(any(String.class), eq("product.created"));

		// when
		ProductCreatedMessage message = ProductCreatedMessage.builder()
						.messageUuid(UUID.randomUUID().toString())
						.product(Product.builder()
										.id(42L)
										.name("Batata")
										.build())
						.build();
		messageProvider.providerProductCreatedMessage(message);

		// then
		ArgumentCaptor<String> messageCapture = ArgumentCaptor.forClass(String.class);
		verify(publisher, times(1)).publishMessage(messageCapture.capture(), eq("product.created"));

		// returning the message
		return messageCapture.getValue();
	}
}
