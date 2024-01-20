package com.tapestry.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tapestry.views.auth.LoginEntity;
import com.tapestry.views.auth.RegistrationEntity;

@Service
public class AuthenticationService
{

	@Autowired
	HttpClient client;

	public void getCurrentUser()
	{

	}

	/**
	 * Login with username/pw
	 *
	 * @public
	 */
	public void login(final LoginEntity payload)
	{
	}

	public void logout()
	{
		// Do we need to provide the token or can we get it here?
	}

	/**
	 * Register a new user
	 *
	 * @public
	 */
	public void register(final RegistrationEntity payload)
	{

	}

	public void resetPassword()
	{
		//
	}
}
