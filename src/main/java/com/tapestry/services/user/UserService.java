package com.tapestry.services.user;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tapestry.models.user.User;
import com.tapestry.services.ServiceCallBack;
import com.tapestry.services.ServiceSkeleton;
import com.tapestry.services.user.types.AuthenticateRequest;
import com.tapestry.services.user.types.CreateUserRequest;
import com.tapestry.services.user.types.SendOtpRequest;
import com.tapestry.views.auth.LoginEntity;
import com.tapestry.views.auth.RegistrationEntity;
import com.vaadin.flow.component.page.WebStorage;

@Service
public class UserService extends ServiceSkeleton
{

	private static final String STORAGE_AUTH_KEY = "auth";

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
		WebStorage.getItem(UserService.STORAGE_AUTH_KEY, token ->
		{
			final var result = this.client.get(token);
			this.updateStorage("getCurrentUser", result);
			callBack.onResponse(result.getStatusCode().isError(), result.getBody());
		});
	}

	public void loggedIn(final ServiceCallBack<Boolean> callBack)
	{
		WebStorage.getItem(UserService.STORAGE_AUTH_KEY, token ->
		{
			final var result = this.client.get(token);
			this.updateStorage("getCurrentUser", result);
			final boolean loggedIn = result.hasBody() ? result.getBody().isAuthenticated() : false;
			callBack.onResponse(result.getStatusCode().isError(), Boolean.valueOf(loggedIn));
		});
	}

	/**
	 * Login with username/pw
	 *
	 * @public
	 */
	public void login(final LoginEntity payload, final ServiceCallBack<User> callBack)
	{
		// this.execute(() ->
		{
			final var request = AuthenticateRequest.builder().userName(payload.getPhone()).password(payload.getPassword()).build();
			final var result = this.client.authenticate(request);
			this.updateStorage("login", result);

			callBack.onResponse(result.getStatusCode().isError(), result.getBody());
		}

		// return result;
		// }, callBack);
	}

	public void logout(final ServiceCallBack<User> callBack)
	{
		WebStorage.getItem(UserService.STORAGE_AUTH_KEY, token ->
		{
			final var result = this.client.logout(token);
			this.updateStorage("logout", result);
			callBack.onResponse(false, null);
		});
	}

	/**
	 * Register a new user
	 *
	 * @public
	 */
	public void register(final RegistrationEntity payload, final ServiceCallBack<User> callBack)
	{
		this.execute(() ->
		{
			final CreateUserRequest request = CreateUserRequest.builder().emailAddress(payload.getEmail()).firstName(payload.getFirstName()).lastName(payload.getLastName()).mobileNumber(payload.getPhone()).build();
			final var result = this.client.create(request);
			this.updateStorage("register", result);
			return result;
		}, callBack);
	}

	public void resetPassword(final String userName, final ServiceCallBack<User> callBack)
	{
		this.execute(() ->
		{
			final SendOtpRequest request = SendOtpRequest.builder().userName(userName).build();
			final var result = this.client.sendOtp(request);
			this.updateStorage("resetPassword", result);
			return result;
		}, callBack);
	}

	private void updateStorage(final String title, final ResponseEntity<User> result)
	{
		if (result.getStatusCode().isError())
		{
			this.getLogger().warn("Could not process {} : {}", title, result.getStatusCode());
			WebStorage.removeItem(UserService.STORAGE_AUTH_KEY);
		}
		else
		{
			WebStorage.setItem(UserService.STORAGE_AUTH_KEY, result.getBody().getToken());
		}
	}
}
