package com.tapestry.services.hound;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import com.tapestry.models.chat.HoundChat;
import com.tapestry.models.chat.HoundChatMessage;
import com.tapestry.services.ClientSkeleton;
import com.tapestry.services.hound.types.HoundAskRequest;
import com.tapestry.services.hound.types.HoundAskResponse;

@Component
public class HoundClient extends ClientSkeleton
{

	RestClient client;

	@Autowired
	HttpComponentsClientHttpRequestFactory clientHttpRequestFactory;

	public HoundClient()
	{
		super(HoundClient.class);

		this.client = RestClient.builder().baseUrl("http://chi-dev-dev02-a:32642/hound").requestFactory(this.clientHttpRequestFactory).build();
	}

	public ResponseEntity<HoundChatMessage> askMessage(final HoundChat chat, final String message)
	{
		return this.askMessage(chat.getRecordId(), message);
	}

	public ResponseEntity<HoundChatMessage> askMessage(final String chatRecordId, final String message)
	{
		final ResponseEntity<HoundChatMessage> chatMessageResponse = this.addMessage(chatRecordId, message);
		if (chatMessageResponse.getStatusCode().isError())
		{
			return chatMessageResponse;
		}

		final HoundChatMessage chatMessage = chatMessageResponse.getBody();

		final ResponseEntity<HoundAskResponse> askResponse = this.askChatMessage(chatMessage.getChatRecordId());
		if (askResponse.getStatusCode().isError())
		{
			return new ResponseEntity<>((HoundChatMessage) null, HttpStatusCode.valueOf(500));
		}

		return new ResponseEntity<>(askResponse.getBody().getChatMessage(), askResponse.getStatusCode());
	}

	public ResponseEntity<HoundChat> startChat(final String topicRecordId, final String owner)
	{
		final HoundChat request = HoundChat.builder().owner(owner).topicRecordId(topicRecordId).systemMessage("SYSTEM_MESSAGE").build();

		try
		{
			// @formatter:off
			final var response = this.client.post()
					.uri("/chats")
					.contentType(MediaType.APPLICATION_JSON)
					.body(request)
					.accept(MediaType.APPLICATION_JSON)
					.retrieve()
					.toEntity(HoundChat.class);
			// @formatter:on

			this.logIt("start chat", response);

			return response;
		}
		catch (final Exception e)
		{
			this.logException("start chat", e);
			return new ResponseEntity<>((HoundChat) null, HttpStatusCode.valueOf(500));
		}
	}

	private ResponseEntity<HoundChatMessage> addMessage(final String chatRecordId, final String message)
	{
		final HoundChatMessage request = HoundChatMessage.builder().chatRecordId(chatRecordId).prompt(message).build();

		try
		{
			// @formatter:off
				final var response = this.client.post()
						.uri("/chatMessages")
						.contentType(MediaType.APPLICATION_JSON)
						.body(request)
						.accept(MediaType.APPLICATION_JSON)
						.retrieve()
						.toEntity(HoundChatMessage.class);
				// @formatter:on

			this.logIt("chat message", response);

			if (!response.hasBody())
			{
				this.warn("Could not start chat message.");
			}

			return response;
		}
		catch (final Exception e)
		{
			this.logException("send message", e);
			return new ResponseEntity<>((HoundChatMessage) null, HttpStatusCode.valueOf(500));
		}
	}

	private ResponseEntity<HoundAskResponse> askChatMessage(final String chatRecordId)
	{
		final HoundAskRequest request = HoundAskRequest.builder().chatRecordId(chatRecordId).timeout("+5 minutes").build();

		try
		{
			// @formatter:off
				final var response = this.client.post()
						.uri("/chats/ask")
						.contentType(MediaType.APPLICATION_JSON)
						.body(request)
						.accept(MediaType.APPLICATION_JSON)
						.retrieve()
						.toEntity(HoundAskResponse.class);
				// @formatter:on

			this.logIt("ask chat message", response);

			if (response.getStatusCode().isError())
			{
				this.warn("Could not ask chat message.");
			}

			return response;
		}
		catch (final Exception e)
		{
			this.logException("ask chat message", e);
			return new ResponseEntity<>((HoundAskResponse) null, HttpStatusCode.valueOf(500));
		}
	}

}
