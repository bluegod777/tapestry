package com.tapestry.views;

import org.springframework.beans.factory.annotation.Autowired;

import com.tapestry.services.user.UserService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H5;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.theme.lumo.LumoUtility;

public class AppNavbar extends HorizontalLayout
{

  // TODO: how to not have to pass in constructor when init new component, see
  // AppLayout
  public AppNavbar(@Autowired final UserService userService)
  {
    HorizontalLayout left = new HorizontalLayout();
    left.setAlignItems(Alignment.CENTER);
    left.addClassName("app-navbar-menu");

    // MOCK
    left.add(new H5("HOME"));
    left.add(new H5("CHILDREN"));
    left.add(new H5("TBUCKS"));
    left.add(new H5("LEARN"));
    left.add(new H5("ACCOUNT"));

    HorizontalLayout right = new HorizontalLayout();
    right.setAlignItems(Alignment.CENTER);
    right.setJustifyContentMode(JustifyContentMode.END);

    Button logoutBtn = new Button("Logout", r ->
    {
      userService.logout(null);
    });

    right.add(logoutBtn);

    final Image logo = new Image("images/tapestry-t-navbar-logo.png", "Tapestry");
    logo.setWidth("2em");
    logo.addClassName(LumoUtility.Margin.Left.MEDIUM);
    right.add(logo);

    this.add(left, right);
  }
}
