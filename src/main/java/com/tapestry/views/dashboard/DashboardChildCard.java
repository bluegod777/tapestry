package com.tapestry.views.dashboard;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.H6;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.theme.lumo.LumoUtility;

public class DashboardChildCard extends VerticalLayout
{
  public DashboardChildCard()
  {
    this.setHeight("300px");
    this.setWidth("230px");
    this.setMinWidth("230px");
    this.addClassName("dashboard-child-card");

    VerticalLayout top = new VerticalLayout();
    top.setPadding(false);
    top.addClassName(LumoUtility.Flex.AUTO);

    top.add(new H3("Child"));
    top.add(new H4("Stories"));
    top.add(new H6("How finances work in the world"));
    top.add(new H6("Investing 101"));
    top.add(new H6("Work smart not hard"));

    Div bot = new Div();
    Button newStoryButton = new Button("Send TBucks");
    newStoryButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
    bot.add(newStoryButton);

    this.add(top, bot);
  }
}
