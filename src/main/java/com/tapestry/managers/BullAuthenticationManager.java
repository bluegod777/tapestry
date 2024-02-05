package com.tapestry.managers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.tapestry.models.user.User;
import com.tapestry.services.user.UserClient;
import com.tapestry.services.user.types.AuthenticateRequest;

@Component
public class BullAuthenticationManager implements AuthenticationManager
{
	@Autowired
	UserClient userClient;

	private final Logger logger = LoggerFactory.getLogger(BullAuthenticationManager.class);

	@Override
	public Authentication authenticate(final Authentication authentication) throws AuthenticationException
	{
		this.logger.info("Authenticate called {}", authentication);

		if (authentication instanceof final UsernamePasswordAuthenticationToken token)
		{

			final AuthenticateRequest request = AuthenticateRequest.builder().userName(token.getName()).password(token.getCredentials().toString()).build();

			final ResponseEntity<User> result = this.userClient.authenticate(request);
			if (result.getStatusCode().isError())
			{
				this.logger.warn("Invalid authentication");
				throw new AuthenticationCredentialsNotFoundException("Not valid");
			}

			final User user = result.getBody();

			return UsernamePasswordAuthenticationToken.authenticated(user, "", user.getAuthorities());
		}

		return null;
	}

}
