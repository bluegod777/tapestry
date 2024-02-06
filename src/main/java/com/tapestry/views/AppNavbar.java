package com.tapestry.views;

import org.springframework.beans.factory.annotation.Autowired;

import com.tapestry.components.MatIcon;
import com.tapestry.services.user.UserService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H5;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.page.WebStorage;
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

    final H5 homeBtn = new H5("HOME");
    homeBtn.addClickListener(ev -> UI.getCurrent().navigate(""));
    left.add(homeBtn);

    final H5 tbucksBtn = new H5("TBUCKS");
    tbucksBtn.addClickListener(ev -> UI.getCurrent().navigate("tbucks"));
    left.add(tbucksBtn);

    final H5 learnBtn = new H5("LEARN");
    learnBtn.addClickListener(ev -> UI.getCurrent().navigate("learn"));
    left.add(learnBtn);

    final H5 accountBtn = new H5("ACCOUNT");
    accountBtn.addClickListener(ev -> UI.getCurrent().navigate("account"));
    left.add(accountBtn);

    final H5 supportBtn = new H5("SUPPORT");
    supportBtn.addClickListener(ev -> UI.getCurrent().navigate("support"));
    left.add(supportBtn);

    HorizontalLayout right = new HorizontalLayout();
    right.setAlignItems(Alignment.CENTER);
    right.setJustifyContentMode(JustifyContentMode.END);

    MatIcon themeSwitch = new MatIcon("light_mode");

    // On load
    WebStorage.getItem("theme", value ->
    {
      if (value != null && value.contains("dark"))
      {
        themeSwitch.replace("dark_mode");
        UI.getCurrent().getElement().getThemeList().add("dark");
      }
    });

    // On click
    themeSwitch.addClickListener(ev ->
    {
      WebStorage.getItem("theme", value ->
      {
        if (value == null || !value.contains("dark"))
        {
          themeSwitch.replace("dark_mode");
          WebStorage.setItem("theme", value + " dark");
          UI.getCurrent().getElement().getThemeList().add("dark");
        } else
        {
          themeSwitch.replace("light_mode");
          WebStorage.setItem("theme", value.replace("dark", "").trim());
          UI.getCurrent().getElement().getThemeList().remove("dark");
        }
      });
    });

    right.add(themeSwitch);

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