package com.tapestry.security;

import java.util.Optional;

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
		System.out.println("We are here!");
		// return this.authenticationContext.getAuthenticatedUser(UserDetails.class).map(userDetails -> this.userRepository.findByUsername(userDetails.getUsername()).orElseGet(() -> null));

		return null;
	}

	public void logout()
	{
		this.authenticationContext.logout();
	}

}
