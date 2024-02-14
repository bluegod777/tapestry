package com.tapestry.services.relationship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import com.tapestry.models.entity.relationships.Relationship;
import com.tapestry.models.entity.relationships.RelationshipRecordState;
import com.tapestry.models.entity.relationships.RelationshipType;
import com.tapestry.services.user.UserClient;

@SpringBootTest
public class RelationshipClientTest {

	@Autowired
	final RelationshipClient client = new RelationshipClient();
	
	@Autowired
	final UserClient userClient = new UserClient();

	@Test
	public void crudTest()
	{
		String token;
		Relationship relationship;

		{
			var result = userClient.authenticate("7758309851", "abcdefg");
			assertEquals(HttpStatus.OK, result.getStatusCode(), "The status code should be OK");
			token = result.getBody().getToken();
			assertTrue(!token.isEmpty(), "The token should not be empty.");
		}
		{
			//@formatter:off
			var newRelationship = Relationship.builder()
				.aEntityRecordId("11111111-2222-3333-4444-55555555555")
				.bEntityRecordId("11111111-2222-3333-4444-66666666666")
				.state(RelationshipRecordState.ACTIVE)
				.type(RelationshipType.LEGAL_GUARDIAN)
				.build();
			//@formatter:on
		
			var result = client.addOrUpdate(token, newRelationship);
			assertEquals(HttpStatus.OK, result.getStatusCode(), "The status code should be OK");
			
			relationship = result.getBody();
		}
		{
			var result = client.getRelationships(token, "11111111-2222-3333-4444-666666666666", RelationshipType.ALL, true, "HEAVY");
			assertEquals(HttpStatus.OK, result.getStatusCode(), "The status code should be OK");
		}
		{
			var result = client.delete(token, relationship);
			assertEquals(HttpStatus.OK, result.getStatusCode(), "The status code should be OK");
		}
	}
	
}
