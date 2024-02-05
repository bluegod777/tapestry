package com.tapestry.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.tapestry.models.entity.Entity;
import com.tapestry.services.entity.EntityClient;
import com.tapestry.services.user.UserClient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

@SpringBootTest
public class EntityClientTest
{

	@Autowired
	final EntityClient client = new EntityClient();

	@Autowired
	final UserClient userClient = new UserClient();

	@Test
	void testGetEntity()
	{
		String token;
		
		{
			var result = userClient.authenticate("7758309851", "abcdefg");	
			assertEquals(HttpStatus.OK, result.getStatusCode(), "The status code should be OK");
			token = result.getBody().getToken();
			assertTrue(!token.isEmpty(), "The token should not be empty.");
		}
		
		Entity entity;
		
		{
			var result2 = client.getEntity(token, "7758309851");
			assertEquals(HttpStatus.OK, result2.getStatusCode(), "The status code should be OK");
		
			entity = result2.getBody();
		}
		
		{
			var result = client.getEntityByRecordId(token, entity.getRecordId());
			assertEquals(HttpStatus.OK, result.getStatusCode(), "The status code should be OK");
		}
		
		{
			var result = client.update(token, entity);
			assertEquals(HttpStatus.OK, result.getStatusCode(), "The status code should be OK");
		}
	}

}
