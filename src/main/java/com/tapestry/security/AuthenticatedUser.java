package com.tapestry.security;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.tapestry.data.entity.User;
import com.vaadin.flow.spring.security.AuthenticationContext;

@Component
public class AuthenticatedUser
{

	// private final IUserRepository userRepository;
	private final AuthenticationContext authenticationContext;

	// -------------------------------------------------------------------
	//
	// -------------------------------------------------------------------
	// public AuthenticatedUser(AuthenticationContext authenticationContext, IUserRepository userRepository)
	public AuthenticatedUser(AuthenticationContext authenticationContext)
	{
		// this.userRepository = userRepository;
		this.authenticationContext = authenticationContext;
	}

	// @Transactional
	public Optional<User> get()
	{
		switch (this.authenticationContext.getAuthenticatedUser(UserDetails.class).get().getUsername())
		{
		case "parent":
			return Optional.of(User.builder().firstName("Sally").lastName("Parent").email("omey@tapestrycard.com").mobilePhoneNumber("3605613793").build());

		default:
			return Optional.of(null);
		}
	}

	public void logout()
	{
		this.authenticationContext.logout();
	}

}
