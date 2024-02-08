package com.tapestry.services.content;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import com.tapestry.models.content.Content;
import com.tapestry.models.content.ContentSearchRequest;
import com.tapestry.models.content.ContentSearchResponse;
import com.tapestry.services.ClientSkeleton;

@Component
public class ContentClient  extends ClientSkeleton
{

	@Autowired
	RestClient client;

	public ContentClient()
	{
		super(ContentClient.class);
	}
	
	public ResponseEntity<Content> add(String token, Content content)
	{
		if (token.isEmpty())
		{
			warn("The Token cannot be the empty string.");
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		if ( content.getContentType().isEmpty())
		{
			warn("The Content Type cannot be the empty string.");
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		try
		{
			//@formatter:off
			final var response = this.client.post()
				.uri("/content/add")
				.header("Authorization", token)
				.accept(MediaType.APPLICATION_JSON)
				.body(content)
				.retrieve()
				.toEntity(Content.class);
			//@formatter:on

			this.logIt("update", response);

			return response;
		} catch (final Exception e)
		{
			this.logException("add", e);
			return new ResponseEntity<>((Content) null, HttpStatusCode.valueOf(500));
		}
	}
	
	public ResponseEntity<Void> delete(String token, Content content)
	{
		if (token.isEmpty())
		{
			warn("The Token cannot be the empty string.");
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		if (content.getRecordId().isEmpty())
		{
			warn("The Content Record ID cannot be the empty string.");
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		try
		{
			//@formatter:off
			this.client.delete()
				.uri("/content/" + content.getRecordId())
				.header("Authorization", token)
				.accept(MediaType.APPLICATION_JSON)
				.retrieve();
			//@formatter:on

			return new ResponseEntity<>((Void) null, HttpStatusCode.valueOf(200));
		} catch (final Exception e)
		{
			this.logException("delete", e);
			return new ResponseEntity<>((Void) null, HttpStatusCode.valueOf(500));
		}
	}
	
	public ResponseEntity<ContentSearchResponse> getContent(String token, ContentSearchRequest request)
	{
		try
		{
			//@formatter:off
			final var response = this.client.post()
				.uri("/content/search")
				.header("Authorization", token)
				.accept(MediaType.APPLICATION_JSON)
				.body(request)
				.retrieve()
				.toEntity(ContentSearchResponse.class);
			//@formatter:on

			this.logIt("search", response);

			return response;
		} catch (final Exception e)
		{
			this.logException("add", e);
			return new ResponseEntity<>((ContentSearchResponse) null, HttpStatusCode.valueOf(500));
		}
	}
	
}
