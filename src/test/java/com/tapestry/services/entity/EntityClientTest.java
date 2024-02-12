package com.tapestry.services.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import com.tapestry.models.entity.Entity;
import com.tapestry.services.user.UserClient;

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
			System.out.println("XXX Delete " + entity);
			var result = client.update(token, entity);
			assertEquals(HttpStatus.OK, result.getStatusCode(), "The status code should be OK");
		}
	}

}
