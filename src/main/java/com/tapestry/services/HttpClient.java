package com.tapestry.services;

import org.springframework.http.client.JettyClientHttpRequestFactory;
import org.springframework.web.client.RestClient;

public class HttpClient {
  private RestClient restClient;

  public HttpClient() {
    // Add interceptors or converters as needed
    RestClient customClient = RestClient.builder()
        .requestFactory(new JettyClientHttpRequestFactory())
        .baseUrl("https://global-dev.ayt360.org/bull")
        .build();

    this.restClient = customClient;
  }

  public RestClient getClient() {
    return this.restClient;
  }
}
