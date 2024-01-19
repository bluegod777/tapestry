package com.tapestry.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.web.client.RestClient;

@SpringBootTest
@Import(
{
		HttpClientConfig.class
})
public class HttpClientTest
{
	@Autowired
	RestClient restClient;
}
