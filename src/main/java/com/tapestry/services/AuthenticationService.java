package com.tapestry.services;

import org.springframework.http.MediaType;

import com.tapestry.views.auth.LoginEntity;
import com.tapestry.views.auth.RegistrationEntity;

public class AuthenticationService {

  HttpClient httpClient = new HttpClient();

  /**
   * Register a new user
   * 
   * @public
   */
  public void register(RegistrationEntity payload) {
    httpClient.getClient().post()
        .uri("/users/create")
        .accept(MediaType.APPLICATION_JSON)
        .retrieve()
        .body(String.class);
  }

  /**
   * Login with username/pw
   * 
   * @public
   */
  public void login(LoginEntity payload) {
    httpClient.getClient().post()
        .uri("/users/authenticate")
        .accept(MediaType.APPLICATION_JSON)
        .retrieve()
        .body(String.class);
  }

  public void logout() {
    // Do we need to provide the token or can we get it here?
  }

  public void resetPassword() {
    //
  }

  public void getCurrentUser() {

  }
}
