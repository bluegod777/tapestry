package com.tapestry.services.entity;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tapestry.models.entity.Entity;
import com.tapestry.models.user.User;
import com.tapestry.services.ServiceCallBack;
import com.tapestry.services.ServiceSkeleton;
import com.vaadin.flow.spring.security.AuthenticationContext;

@Service
public class EntityService extends ServiceSkeleton
{

	@Autowired
	AuthenticationContext authContext;

	@Autowired
	EntityClient client;

	public EntityService()
	{
		super(EntityService.class);
	}

	public void getEntity(final String username, final ServiceCallBack<Entity> callBack)
	{
		info("API Call: Get Entity");
		info("    Username: %s", username);
		
		final Optional<User> optional = this.authContext.getAuthenticatedUser(User.class);
		info("    Optional: %s", optional);
		if (optional.isPresent())
		{
			final var result = this.client.getEntity(optional.get().getToken(), username);
			callBack.onResponse(result.getStatusCode().isError(), result.getBody());
		}
	}

	public void update(final Entity entity, final ServiceCallBack<Entity> callBack)
	{
		final Optional<User> optional = this.authContext.getAuthenticatedUser(User.class);
		if (optional.isPresent())
		{
			final var result = this.client.update(optional.get().getToken(), entity);
			callBack.onResponse(result.getStatusCode().isError(), result.getBody());
		}
	}

}
