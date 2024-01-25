package com.tapestry.managers;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.tapestry.models.user.User;
import com.tapestry.models.user.enums.Role;
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
				return null;
			}

			final List<GrantedAuthority> authorities = new ArrayList<>();

			for (final Role role : result.getBody().getRoles())
			{
				final GrantedAuthority authority = new SimpleGrantedAuthority(role.name());
				authorities.add(authority);
			}

			final UserDetails userDetails = new org.springframework.security.core.userdetails.User(token.getName(), "", authorities);

			return UsernamePasswordAuthenticationToken.authenticated(userDetails, "", authorities);
		}

		return null;
	}

}
