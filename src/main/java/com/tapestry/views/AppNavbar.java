package com.tapestry.views;

import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H6;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

public class AppNavbar extends HorizontalLayout
{
  public AppNavbar()
  {
    this.add(new H6("HOME"));
    this.add(new H6("CHILDREN"));
    this.add(new H6("TBUCKS"));
    this.add(new H6("LEARN"));
    this.add(new H6("ACCOUNT"));
  }
}
