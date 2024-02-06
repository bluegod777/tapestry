package com.tapestry.views.dashboard;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class DashboardStoryCard extends VerticalLayout
{
  public DashboardStoryCard()
  {
    this.setHeight("300px");
    this.setWidth("400px");
    this.setMinWidth("400px");
    this.addClassName("dashboard-story-card");
  }
}
