package com.tapestry.services.content;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.tapestry.models.content.Content;
import com.tapestry.models.user.User;
import com.tapestry.services.ServiceCallBack;
import com.tapestry.services.ServiceSkeleton;
import com.vaadin.flow.spring.security.AuthenticationContext;

public class ContentService extends ServiceSkeleton {

	@Autowired
	AuthenticationContext authContext;

	@Autowired
	ContentClient client;

	public ContentService()
	{
		super(ContentService.class);
	}
	
	public void add(final Content content, final ServiceCallBack<Content> callBack)
	{
		info("API Call: Add Content");
		info("    Content: %s", content);
		
		final Optional<User> optional = this.authContext.getAuthenticatedUser(User.class);
		info("    Optional: %s", optional);
		if (optional.isPresent())
		{
			final var result = this.client.add(optional.get().getToken(), content);
			callBack.onResponse(result.getStatusCode().isError(), result.getBody());
		}
	}
	
	public void delete(final Content content, final ServiceCallBack<Void> callBack)
	{
		info("API Call: Delete Content");
		info("    Content: %s", content);
		
		final Optional<User> optional = this.authContext.getAuthenticatedUser(User.class);
		info("    Optional: %s", optional);
		if (optional.isPresent())
		{
			final var result = this.client.delete(optional.get().getToken(), content);
			callBack.onResponse(result.getStatusCode().isError(), result.getBody());
		}
	}
	
}
