package com.tapestry.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class HttpClientTest
{

	@Test
	void accountInUse()
	{
		final HttpClient client = new HttpClient();
		Assertions.assertTrue(client.isAccountInUse("7758309851"));
		Assertions.assertFalse(client.isAccountInUse("9998309851"));
	}

}
