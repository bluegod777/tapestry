package com.tapestry.services.user;

import java.util.Optional;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.tapestry.models.user.User;
import com.tapestry.services.ServiceCallBack;
import com.tapestry.services.ServiceSkeleton;
import com.tapestry.services.user.types.AuthenticateRequest;
import com.tapestry.services.user.types.CreateUserRequest;
import com.tapestry.services.user.types.SendOtpRequest;
import com.tapestry.views.auth.LoginEntity;
import com.tapestry.views.auth.RegistrationEntity;
import com.vaadin.flow.spring.security.AuthenticationContext;

@Service
public class UserService extends ServiceSkeleton
{

	@Autowired
	AuthenticationContext authContext;

	@Autowired
	UserClient client;

	public UserService()
	{
		super(LoggerFactory.getLogger(UserService.class));
	}

	public void getCurrentUser(final ServiceCallBack<User> callBack)
	{
		// TODO: @fbarrie-knowtal, this is pretty common, where should it go
		// NOT SURE
		final Optional<User> optional = this.authContext.getAuthenticatedUser(User.class);
		if (optional.isPresent())
		{
			callBack.onResponse(false, optional.get());
		}
		else
		{
			callBack.onResponse(true, null);
		}
	}

	public void loggedIn(final ServiceCallBack<Boolean> callBack)
	{

		final Optional<User> optional = this.authContext.getAuthenticatedUser(User.class);
		if (optional.isPresent())
		{
			final var result = this.client.get(optional.get().getToken());
			this.updateStorage("getCurrentUser", result);
			final boolean loggedIn = result.hasBody() ? result.getBody().isAuthenticated() : false;
			callBack.onResponse(result.getStatusCode().isError(), Boolean.valueOf(loggedIn));
		}
		else
		{
			callBack.onResponse(true, null);
		}
	}

	/**
	 * Login with username/pw
	 *
	 * @public
	 */
	public void login(final LoginEntity payload, final ServiceCallBack<User> callBack)
	{
		final var request = AuthenticateRequest.builder().userName(payload.getPhone()).password(payload.getPassword()).build();
		final var result = this.client.authenticate(request);
		this.updateStorage("login", result);

		callBack.onResponse(result.getStatusCode().isError(), result.getBody());
	}

	public void logout(final ServiceCallBack<User> callBack)
	{
		final Optional<User> optional = this.authContext.getAuthenticatedUser(User.class);
		if (optional.isPresent())
		{
			final var result = this.client.logout(optional.get().getToken());
			this.updateStorage("logout", result);
		}

		this.authContext.logout();
		callBack.onResponse(false, null);
	}

	/**
	 * Register a new user
	 *
	 * @public
	 */
	public void register(final RegistrationEntity payload, final ServiceCallBack<User> callBack)
	{

		final CreateUserRequest request = CreateUserRequest.builder().emailAddress(payload.getEmail()).firstName(payload.getFirstName()).lastName(payload.getLastName()).mobileNumber(payload.getPhone()).build();
		final var result = this.client.create(request);
		this.updateStorage("register", result);
		callBack.onResponse(result.getStatusCode().isError(), result.getBody());

	}

	public void resetPassword(final String userName, final ServiceCallBack<User> callBack)
	{
		final SendOtpRequest request = SendOtpRequest.builder().userName(userName).build();
		final var result = this.client.sendOtp(request);
		this.updateStorage("resetPassword", result);
		callBack.onResponse(result.getStatusCode().isError(), result.getBody());
	}

	private void updateStorage(final String title, final ResponseEntity<User> result)
	{
		if (result.getStatusCode().isError())
		{
			this.getLogger().warn("Could not process {} : {}", title, result.getStatusCode());
			this.authContext.logout();
		}
		else
		{
			this.authContext.getAuthenticatedUser(User.class).ifPresent(user ->
			{
				SecurityContextHolder.getContext().setAuthentication(UsernamePasswordAuthenticationToken.authenticated(user, "", user.getAuthorities()));
			});
		}
	}
}
