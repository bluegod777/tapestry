package com.tapestry.views.onboarding;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Welcome")
@Route(value = "welcome")
public class OnboardingWelcomeView extends VerticalLayout {

  public OnboardingWelcomeView() {
    add(new H1("Welcome!"));
  }
}
