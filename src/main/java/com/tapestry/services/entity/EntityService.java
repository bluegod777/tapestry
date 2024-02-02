package com.tapestry.services.entity;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.tapestry.models.entity.Entity;
import com.tapestry.models.user.User;
import com.tapestry.services.ServiceCallBack;
import com.tapestry.services.ServiceSkeleton;
import com.vaadin.flow.spring.security.AuthenticationContext;

public class EntityService extends ServiceSkeleton {

	@Autowired
	AuthenticationContext authContext;

	@Autowired
	EntityClient client;

	public EntityService() {
		super(EntityService.class);
	}
	
	public void getEntity(final String userName, final ServiceCallBack<Entity> callBack)
	{
		final Optional<User> optional = this.authContext.getAuthenticatedUser(User.class);
		if ( optional.isPresent())
		{
			final var result = this.client.getEntity(optional.get().getToken(), userName);
			callBack.onResponse(result.getStatusCode().isError(), result.getBody());
		}
	}

}
