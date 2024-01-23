package com.tapestry.views.onboarding;

import org.springframework.beans.factory.annotation.Autowired;

import com.tapestry.services.user.UserService;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.WebStorage;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@PageTitle("Welcome") @Route(value = "welcome") @AnonymousAllowed
public class OnboardingWelcomeView extends VerticalLayout
{

  @Autowired
  UserService userService;

  public OnboardingWelcomeView()
  {
    // TODO: @fbarrie-knowtal this is null?
    // userService.getCurrentUser();

    WebStorage.getItem("bull.auth", token ->
    {
      // TODO: could be null
      System.out.println("token: " + token);
    });

    add(new H1("Welcome!"));
  }
}
