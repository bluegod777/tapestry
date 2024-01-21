package com.tapestry.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tapestry.data.entity.User;

@SpringBootTest
public class UserClientTest
{
	@Autowired
	final UserClient client = new UserClient();

	@Test
	void accountInUse()
	{
		Assertions.assertTrue(this.client.isAccountInUse("7758309851"));
		Assertions.assertFalse(this.client.isAccountInUse("9998309851"));
	}

	void authenticate()
	{
		final var response = this.client.authenticate("7758309851", "abcdefg");
		Assertions.assertTrue(response.getStatusCode().is2xxSuccessful());
		final User user = response.getBody();
		Assertions.assertEquals("Fred Barrie", user.getName());
		Assertions.assertTrue(user.isAuthenticated());
	}

	void get()
	{
		var response = this.client.authenticate("7758309851", "abcdefg");
		Assertions.assertTrue(response.getStatusCode().is2xxSuccessful());

		response = this.client.get(response.getBody().getToken());
		Assertions.assertTrue(response.getStatusCode().is2xxSuccessful());

		final User user = response.getBody();
		Assertions.assertEquals("Fred Barrie", user.getName());
		Assertions.assertTrue(user.isAuthenticated());
	}

	void sendOtp()
	{
		final var response = this.client.sendOtp("7758309851");
		Assertions.assertTrue(response.getStatusCode().is2xxSuccessful());

		final User user = response.getBody();
		Assertions.assertEquals("Fred Barrie", user.getName());
		Assertions.assertFalse(user.isAuthenticated());
		System.out.println(user.getToken());
	}

	void validateOtp()
	{
		final String token = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJyZWNvcmRJZCI6IjIyRTRGQTlFLUEzQUUtNEIxNy1BNjQ0LUQxMEIzRjJGMDEyOCIsInN1YiI6Ijc3NTgzMDk4NTEiLCJhdXRoZW50aWNhdGVkIjoiZmFsc2UiLCJyb2xlcyI6WyJVU0VSIl0sIm5hbWUiOiJGcmVkIEJhcnJpZSIsImV4cCI6MTcwNTg1MzUyNSwiaWF0IjoxNzA1NzY3MTI1fQ.PSgU47lip1iFseRYndCengfMuZZLN3IIp6VcoQ3zhZk";
		final String otpPassword = "040879";

		final var response = this.client.validateOtp(token, otpPassword);
		Assertions.assertTrue(response.getStatusCode().is2xxSuccessful());

		final User user = response.getBody();
		Assertions.assertEquals("Fred Barrie", user.getName());
		Assertions.assertTrue(user.isAuthenticated());
		System.out.println(user.getToken());
	}

}
