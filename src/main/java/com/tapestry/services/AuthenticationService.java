package com.tapestry.services;

import org.springframework.http.MediaType;

import com.tapestry.views.auth.LoginEntity;
import com.tapestry.views.auth.RegistrationEntity;

public class AuthenticationService {

  HttpClient httpClient = new HttpClient();

  // TODO: handling errors generically for the app as well as specific use
  // conditions
  // .onStatus(HttpStatusCode::is4xxClientError, (request, response) -> {
  // throw new MyCustomRuntimeException(response.getStatusCode(),
  // response.getHeaders())
  // })

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
    //
  }

  public void usernameExists() {
    // /users/isAccountAlreadyInUse
  }
}
