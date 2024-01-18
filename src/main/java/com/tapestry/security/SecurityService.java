package com.tapestry.security;

import com.vaadin.flow.spring.security.AuthenticationContext;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class SecurityService {
  private final AuthenticationContext authenticationContext;

  public SecurityService(AuthenticationContext authenticationContext) {
    this.authenticationContext = authenticationContext;
  }

  public UserDetails getAuthenticatedUser() {
    return authenticationContext.getAuthenticatedUser(UserDetails.class).orElse(null);
  }

  public Boolean loggedIn() {
    return getAuthenticatedUser() != null;
  }

  public void logout() {
    authenticationContext.logout();
  }
}