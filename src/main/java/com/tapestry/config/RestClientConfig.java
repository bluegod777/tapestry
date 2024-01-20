package com.tapestry.config;

import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig
{

	@Value("${bull.url:https://global-dev.ayt360.org/bull}")
	String baseUrl;

	@Autowired
	CloseableHttpClient httpClient;

	@Bean
	public HttpComponentsClientHttpRequestFactory clientHttpRequestFactory()
	{
		final HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
		clientHttpRequestFactory.setHttpClient(this.httpClient);
		return clientHttpRequestFactory;
	}

	@Bean
	RestClient restClient()
	{

		return RestClient.builder().baseUrl(this.baseUrl)
				// .requestInterceptor(...)
				// .defaultHeader("AUTHORIZATION", fetchToken())
				// .messageConverters(...)
				.requestFactory(this.clientHttpRequestFactory()).build();
	}

}
