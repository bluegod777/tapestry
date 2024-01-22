package com.tapestry.services.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import com.tapestry.models.user.User;
import com.tapestry.services.user.types.AccountInUseRequest;
import com.tapestry.services.user.types.AuthenticateRequest;
import com.tapestry.services.user.types.ChangePasswordRequest;
import com.tapestry.services.user.types.CreateUserRequest;
import com.tapestry.services.user.types.SendOtpRequest;
import com.tapestry.services.user.types.ValidateOtpRequest;

@Component
public class UserClient
{
	@Autowired
	RestClient client;

	Logger logger = LoggerFactory.getLogger(UserClient.class);

	public ResponseEntity<User> authenticate(final AuthenticateRequest request)
	{
		try
		{
			// @formatter:off
			final var response = this.client.post()
					.uri("/users/authenticate")
					.contentType(MediaType.APPLICATION_JSON)
					.body(request)
					.accept(MediaType.APPLICATION_JSON)
					.retrieve()
					.toEntity(User.class);
			// @formatter:on

			this.logIt("authenticate", response);
			return response;
		} catch (final Exception e)
		{
			this.logException("authenticate", e);
			return new ResponseEntity<>((User) null, HttpStatusCode.valueOf(500));
		}
	}

	public ResponseEntity<User> authenticate(final String userName, final String password)
	{
		final AuthenticateRequest request = AuthenticateRequest.builder().userName(userName).password(password).build();
		return this.authenticate(request);
	}

	public ResponseEntity<User> changePassword(final String token, final ChangePasswordRequest request)
	{
		try
		{
			// @formatter:off
			final var response = this.client.post()
				.uri("/users/changePassword")
				.header("Authorization", token)
				.contentType(MediaType.APPLICATION_JSON)
				.body(request)
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.toEntity(User.class);
			// @formatter:on

			this.logIt("changePassword", response);

			return response;
		} catch (final Exception e)
		{
			this.logException("changePassword", e);
			return new ResponseEntity<>((User) null, HttpStatusCode.valueOf(500));
		}
	}

	public ResponseEntity<User> changePassword(final String token, final String oldPassword, final String newPassword)
	{
		final ChangePasswordRequest request = ChangePasswordRequest.builder().oldPassword(oldPassword)
				.newPassword(newPassword).build();
		return this.changePassword(token, request);
	}

	public ResponseEntity<User> create(final CreateUserRequest request)
	{
		try
		{
			// @formatter:off
			final var response = this.client.post()
					.uri("/users/create")
					.contentType(MediaType.APPLICATION_JSON)
					.body(request)
					.accept(MediaType.APPLICATION_JSON)
					.retrieve()
					.toEntity(User.class);
			// @formatter:on

			this.logIt("create", response);
			return response;
		} catch (final Exception e)
		{
			this.logException("create", e);
			return new ResponseEntity<>((User) null, HttpStatusCode.valueOf(500));
		}
	}

	public ResponseEntity<User> create(final String name, final String mobileNumber, final String emailAddress)
	{
		final CreateUserRequest request = CreateUserRequest.builder().name(name).mobileNumber(mobileNumber)
				.emailAddress(emailAddress).build();
		return this.create(request);
	}

	public ResponseEntity<User> get(final String token)
	{
		try
		{
			// @formatter:off
			final var response = this.client.get()
				.uri("/users")
				.header("Authorization", token)
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.toEntity(User.class);
			// @formatter:on

			this.logIt("get", response);

			return response;
		} catch (final Exception e)
		{
			this.logException("get", e);
			return new ResponseEntity<>((User) null, HttpStatusCode.valueOf(500));
		}
	}

	public boolean isAccountInUse(final AccountInUseRequest request)
	{
		try
		{
			// @formatter:off
			final var response = this.client.post()
					.uri("/users/isAccountAlreadyInUse")
					.contentType(MediaType.APPLICATION_JSON)
					.body(request)
					.accept(MediaType.TEXT_PLAIN)
					.retrieve()
					.toEntity(String.class);
			// @formatter:on

			this.logIt("isAccountInUse", response);

			if (response.getStatusCode().isError())
			{
				return false;
			}

			return Boolean.parseBoolean(response.getBody());
		} catch (final Exception e)
		{
			this.logException("isAccountInUse", e);
			return false;
		}
	}

	public boolean isAccountInUse(final String userName)
	{
		final AccountInUseRequest request = AccountInUseRequest.builder().userName(userName).build();
		return this.isAccountInUse(request);

	}

	public ResponseEntity<User> sendOtp(final SendOtpRequest request)
	{
		try
		{
			// @formatter:off
			final var response = this.client.post()
				.uri("/users/sendOTP")
				.contentType(MediaType.APPLICATION_JSON)
				.body(request)
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.toEntity(User.class);
			// @formatter:on

			this.logIt("sendOtp", response);

			return response;
		} catch (final Exception e)
		{
			this.logException("sendOtp", e);
			return new ResponseEntity<>((User) null, HttpStatusCode.valueOf(500));
		}
	}

	public ResponseEntity<User> sendOtp(final String userName)
	{
		final SendOtpRequest request = SendOtpRequest.builder().userName(userName).build();
		return this.sendOtp(request);
	}

	public ResponseEntity<User> validateOtp(final String token, final String otpPassword)
	{
		final ValidateOtpRequest request = ValidateOtpRequest.builder().otpPassword(otpPassword).build();
		return this.validateOtp(token, request);
	}

	public ResponseEntity<User> validateOtp(final String token, final ValidateOtpRequest request)
	{
		try
		{
			// @formatter:off
			final var response = this.client.post()
				.uri("/users/validate")
				.header("Authorization", token)
				.contentType(MediaType.APPLICATION_JSON)
				.body(request)
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.toEntity(User.class);
			// @formatter:on

			this.logIt("validateOtp", response);

			return response;
		} catch (final Exception e)
		{
			this.logException("validateOtp", e);
			return new ResponseEntity<>((User) null, HttpStatusCode.valueOf(500));
		}
	}

	private void logException(final String title, final Exception e)
	{
		this.logger.warn("Error calling {} : {}", title, e.getMessage());
	}

	private void logIt(final String title, final ResponseEntity<?> response)
	{
		if (response.getStatusCode().isError())
		{
			this.logger.warn("Error calling  {}", title);
			this.logger.warn("   Status Code {}", response.getStatusCode());
		}
	}

}
