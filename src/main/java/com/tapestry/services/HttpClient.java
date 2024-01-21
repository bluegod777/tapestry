package com.tapestry.services;

import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestClient;

import com.tapestry.services.types.AccountInUseRequest;

@Deprecated
public class HttpClient
{
	String bullUrl = "https://global-dev.ayt360.org/bull";

	public boolean isAccountInUse(final String userName)
	{
		final AccountInUseRequest request = AccountInUseRequest.builder().userName(userName).build();
		// @formatter:off
		final var result = this.restClient().post()
				.uri("/users/isAccountAlreadyInUse")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.TEXT_PLAIN)
				.body(request)
				.retrieve()
				.toEntity(String.class);
		// @formatter:on
		return Boolean.parseBoolean(result.getBody());
	}

	HttpComponentsClientHttpRequestFactory clientHttpRequestFactory()
	{
		final HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
		factory.setConnectionRequestTimeout(5000);
		return factory;
	}

	RestClient restClient()
	{
		return RestClient.builder().baseUrl(this.bullUrl)
				// .requestInterceptor(...)
				// .defaultHeader("AUTHORIZATION", fetchToken())
				// .messageConverters(...)
				.requestFactory(this.clientHttpRequestFactory()).build();
	}

}
