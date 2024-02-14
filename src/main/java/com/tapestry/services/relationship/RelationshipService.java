package com.tapestry.services.relationship;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tapestry.models.entity.relationships.RelationSearchResult;
import com.tapestry.models.entity.relationships.Relationship;
import com.tapestry.models.entity.relationships.RelationshipType;
import com.tapestry.models.user.User;
import com.tapestry.services.ServiceCallBack;
import com.tapestry.services.ServiceSkeleton;
import com.vaadin.flow.spring.security.AuthenticationContext;

@Component
public class RelationshipService extends ServiceSkeleton {

	@Autowired
	AuthenticationContext authContext;

	@Autowired
	RelationshipClient client;

	public RelationshipService() {
		super(RelationshipService.class);
	}

	public void addOrUpdate(String token, Relationship relationship, final ServiceCallBack<Relationship> callBack)
	{
		info("API Call: Add/Update Relationship");
		info("    Relationship: %s", relationship);
		
		final Optional<User> optional = this.authContext.getAuthenticatedUser(User.class);
		info("    Optional: %s", optional);
		if (optional.isPresent())
		{
			final var result = this.client.addOrUpdate(optional.get().getToken(), relationship);
			callBack.onResponse(result.getStatusCode().isError(), result.getBody());
		}
	}

	public void delete(final Relationship relationship, final ServiceCallBack<Void> callBack)
	{
		info("API Call: Delete Relationship");
		info("    Content: %s", relationship);
		
		final Optional<User> optional = this.authContext.getAuthenticatedUser(User.class);
		info("    Optional: %s", optional);
		if (optional.isPresent())
		{
			final var result = this.client.delete(optional.get().getToken(), relationship);
			callBack.onResponse(result.getStatusCode().isError(), result.getBody());
		}
	}

	public void getRelationships(String token, String entityRecordId, RelationshipType type, boolean includeEntities, String weight, final ServiceCallBack<RelationSearchResult> callBack)
	{
		info("API Call: Add/Update Relationships");
		info("    Entity Record ID: %s", entityRecordId);
		info("    Type            : %s", type);
		info("    Include Entities: %s", includeEntities);
		info("    Weight          : %s", weight);
		
		final Optional<User> optional = this.authContext.getAuthenticatedUser(User.class);
		info("    Optional: %s", optional);
		if (optional.isPresent())
		{
			final var result = this.client.getRelationships(optional.get().getToken(), entityRecordId, type, includeEntities, weight);
			callBack.onResponse(result.getStatusCode().isError(), result.getBody());
		}
	}
	

}
