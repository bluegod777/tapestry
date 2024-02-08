package com.tapestry.services.content;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import com.tapestry.models.content.Content;
import com.tapestry.models.content.ContentSearchRequest;
import com.tapestry.services.user.UserClient;

@SpringBootTest
public class ContentClientTest {

	
	@Autowired
	final ContentClient client = new ContentClient();
	
	@Autowired
	final UserClient userClient = new UserClient();

	@Test
	public void crudTest()
	{
		String token;

		{
			var result = userClient.authenticate("7758309851", "abcdefg");
			assertEquals(HttpStatus.OK, result.getStatusCode(), "The status code should be OK");
			token = result.getBody().getToken();
			assertTrue(!token.isEmpty(), "The token should not be empty.");
		}
		
		Content content;

		{
			//@formatter:off
			var newContent = Content.builder()
					.category("CATEGORY")
					.content("The quick brown fox jumps over the lazy dog.".getBytes())
					.contentType("text/plain")
					.contentURI("http://www.google.com/")
					.maxAge(99)
					.minAge(12)
					.summary("SUMMARY")
					.title("TITLE")
					.build();
			//@formatter:on
			
			var result = client.add(token, newContent);
			assertEquals(HttpStatus.OK, result.getStatusCode(), "The status code should be OK");
			
			content = result.getBody();
		}
		{
			var request = ContentSearchRequest.builder()
					.category("CATEGORY")
					.build();
			
			var response = client.getContent(token, request);
			assertEquals(HttpStatus.OK, response.getStatusCode(), "The status code should be OK");
			assertTrue(!response.getBody().getContent().isEmpty());
		}
		{
			var result = client.delete(token, content);
			assertEquals(HttpStatus.OK, result.getStatusCode(), "The status code should be OK");
		}
	}
	
}
