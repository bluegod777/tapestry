package com.tapestry.services.entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import com.tapestry.models.entity.Entity;
import com.tapestry.services.ClientSkeleton;

@Component
public class EntityClient extends ClientSkeleton {

	@Autowired
	RestClient client;
	
	public EntityClient()
	{
		super(EntityClient.class);
	}

	Logger logger = LoggerFactory.getLogger(EntityClient.class);
	
	public ResponseEntity<Entity> update(String token, final Entity entity)
	{
		if ( token.isEmpty())
		{
			warn("The Token cannot be the empty string.");
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		if ( entity.getRecordId() == null || entity.getRecordId().isEmpty())
		{
			warn("The Entity Record ID cannot be NULL or the empty string.");
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		try
		{
			//@formatter:off
			final var response = this.client.post()
				.uri("/entities/" + entity.getRecordId())
				.header("Authorization", token)
				.accept(MediaType.APPLICATION_JSON)
				.body(entity)
				.retrieve()
				.toEntity(Entity.class);
			//@formatter:on

			this.logIt("update", response);

			return response;
		}
		catch (final Exception e)
		{
			this.logException("update", e);
			return new ResponseEntity<>((Entity) null, HttpStatusCode.valueOf(500));
		}
	}

	public ResponseEntity<Entity> getEntity(final String token, String username)
	{
		if ( token.isEmpty())
		{
			warn("The Token cannot be the empty string.");
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		if ( username.isEmpty())
		{
			warn("The Username cannot be the empty string.");
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		try
		{
			//@formatter:off
			final var response = this.client.get()
				.uri("/entities/username/" + username)
				.header("Authorization", token)
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.toEntity(Entity.class);
			//@formatter:on

			this.logIt("getEntity", response);

			return response;
		}
		catch (final Exception e)
		{
			this.logException("getEntity", e);
			return new ResponseEntity<>((Entity) null, HttpStatusCode.valueOf(500));
		}
	}

	public ResponseEntity<Entity> getEntityByRecordId(String token, final String recordId)
	{
		if ( token.isEmpty())
		{
			warn("The Token cannot be the empty string.");
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		if ( recordId.isEmpty())
		{
			warn("The Record ID cannot be the empty string.");
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		try
		{
			//@formatter:off
			final var response = this.client.get()
				.uri("/entities/recordId/" + recordId)
				.header("Authorization", token)
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.toEntity(Entity.class);
			//@formatter:on

			this.logIt("getEntity", response);

			return response;
		}
		catch (final Exception e)
		{
			this.logException("getEntity", e);
			return new ResponseEntity<>((Entity) null, HttpStatusCode.valueOf(500));
		}
	}

}
