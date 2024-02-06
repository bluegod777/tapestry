package com.tapestry.views.children;

import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class ChildDetailCurrentTbucksCard extends VerticalLayout
{
  public ChildDetailCurrentTbucksCard()
  {
    this.render();
  }

  private void render()
  {
    this.setWidth("230px");
    this.setMinWidth("230px");
    this.addClassName("child-stat-card");

    this.add(new H2("TBucks"));
    this.add(new H3("$35.00"));
  }
}