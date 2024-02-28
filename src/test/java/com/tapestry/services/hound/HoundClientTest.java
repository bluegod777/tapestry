package com.tapestry.services.hound;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tapestry.models.chat.HoundChat;
import com.tapestry.models.chat.HoundChatMessage;

@SpringBootTest
public class HoundClientTest
{
	private static final String ONTRACK_TOPIC_ID = "01232704-DDA4-4BFB-96A9-39A8FED53744";

	@Autowired
	HoundClient client;

	@Test
	public void testChat()
	{
		final HoundChat chat;
		{
			final var response = this.client.startChat(HoundClientTest.ONTRACK_TOPIC_ID, "unit test at " + ZonedDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));
			Assertions.assertFalse(response.getStatusCode().isError());
			chat = response.getBody();
		}

		HoundChatMessage message;
		{
			final var response = this.client.askMessage(chat, "what is a constituent?");
			Assertions.assertFalse(response.getStatusCode().isError());
			message = response.getBody();
		}

		Assertions.assertTrue(message.getReponseAsString().contains("constituent is a person"));

		{
			final var response = this.client.askMessage(chat, "how do I add one?");
			Assertions.assertFalse(response.getStatusCode().isError());
			message = response.getBody();
		}

		Assertions.assertTrue(message.getReponseAsString().contains("new constituent"));
	}
}
