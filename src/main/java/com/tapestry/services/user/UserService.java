package com.tapestry.services.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tapestry.views.auth.LoginEntity;
import com.tapestry.views.auth.RegistrationEntity;
import com.vaadin.flow.component.page.WebStorage;

@Service
public class UserService
{

  @Autowired
  UserClient client;

  Logger logger = LoggerFactory.getLogger(UserService.class);

  public void getCurrentUser()
  {
    // TODO: @fbarrie-knowtal, this is pretty common, where should it go
    WebStorage.getItem("auth", token ->
    {
      this.logger.info(token);
    });
  }

  /**
   * Login with username/pw
   *
   * @public
   */
  public void login(final LoginEntity payload)
  {
  }

  public void logout()
  {
    // Do we need to provide the token or can we get it here?
  }

  public Boolean loggedIn()
  {
    return false;
  }

  /**
   * Register a new user
   *
   * @public
   */
  public void register(final RegistrationEntity payload)
  {

  }

  public void resetPassword()
  {
    //
  }
}
