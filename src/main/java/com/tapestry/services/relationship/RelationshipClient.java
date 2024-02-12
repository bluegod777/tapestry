package com.tapestry.services.relationship;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import com.tapestry.models.entity.relationships.RelationSearchResult;
import com.tapestry.models.entity.relationships.Relationship;
import com.tapestry.models.entity.relationships.RelationshipType;
import com.tapestry.services.ClientSkeleton;

@Component
public class RelationshipClient extends ClientSkeleton {

	
	@Autowired
	RestClient client;
	
	public RelationshipClient()
	{
		super(RelationshipClient.class);
	}

	public ResponseEntity<Relationship> addOrUpdate(String token, Relationship relationship)
	{
		if (token.isEmpty())
		{
			warn("The Token cannot be the empty string.");
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		if ( relationship.getAEntityRecordId() == null || relationship.getAEntityRecordId().isEmpty())
		{
			warn("The A Record ID is required.");
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		if ( relationship.getBEntityRecordId() == null || relationship.getBEntityRecordId().isEmpty())
		{
			warn("The A Record ID is required.");
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		try
		{
			//@formatter:off
			final var response = this.client.post()
				.uri("/relationships")
				.header("Authorization", token)
				.accept(MediaType.APPLICATION_JSON)
				.body(relationship)
				.retrieve()
				.toEntity(Relationship.class);
			//@formatter:on

			this.logIt("addOrUpdate", response);

			return response;
		} catch (final Exception e)
		{
			this.logException("add", e);
			return new ResponseEntity<>((Relationship) null, HttpStatusCode.valueOf(500));
		}
	}
	
	public ResponseEntity<Void> delete(String token, Relationship relationship)
	{
		if (token.isEmpty())
		{
			warn("The Token cannot be the empty string.");
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		if (relationship.getRecordId().isEmpty())
		{
			warn("The Relationship Record ID cannot be the empty string.");
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		try
		{
			//@formatter:off
			this.client.delete()
				.uri("/relationships/" + relationship.getRecordId())
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

	public ResponseEntity<Relationship> getRelationshipByRecordId(String token, final String recordId)
	{
		if (token.isEmpty())
		{
			warn("The Token cannot be the empty string.");
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		if (recordId.isEmpty())
		{
			warn("The Record ID cannot be the empty string.");
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		try
		{
			//@formatter:off
			final var response = this.client.get()
				.uri("/relationships/recordId/" + recordId)
				.header("Authorization", token)
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.toEntity(Relationship.class);
			//@formatter:on

			this.logIt("getEntity", response);

			return response;
		} catch (final Exception e)
		{
			this.logException("getRelationship", e);
			return new ResponseEntity<>((Relationship) null, HttpStatusCode.valueOf(500));
		}
	}

	public ResponseEntity<RelationSearchResult> getRelationships(String token, String entityRecordId, RelationshipType type, boolean includeEntities, String weight)
	{
		if (token.isEmpty())
		{
			warn("The Token cannot be the empty string.");
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		if (entityRecordId.isEmpty())
		{
			warn("The Entity Record ID cannot be the empty string.");
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		try
		{
			//@formatter:off
			final var response = this.client.get()
				.uri("/relationships/" + entityRecordId + "?type="+ type + "&includeEntities=" + includeEntities + "&weight=" + weight)
				.header("Authorization", token)
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.toEntity(RelationSearchResult.class);
			//@formatter:on

			this.logIt("getRelationships", response);

			return response;
		} catch (final Exception e)
		{
			this.logException("getRelationships", e);
			return new ResponseEntity<>((RelationSearchResult) null, HttpStatusCode.valueOf(500));
		}
	}
	

}
