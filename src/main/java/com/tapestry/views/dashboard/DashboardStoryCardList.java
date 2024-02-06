package com.tapestry.views.dashboard;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.theme.lumo.LumoUtility;

public class DashboardStoryCardList extends VerticalLayout
{
  public DashboardStoryCardList()
  {
    this.setPadding(false);
    this.addClassNames(LumoUtility.Padding.Top.MEDIUM, LumoUtility.Padding.Bottom.MEDIUM);

    Div head = new Div();
    head.add(new H2("Recent Tapestry Stories"));
    head.add(new Paragraph("Lorem Ipsum is simply dummy text of the printing and typesetting industry."));
    this.add(head);

    HorizontalLayout cardList = new HorizontalLayout();
    cardList.addClassName("horizontal-card-list");
    cardList.setSpacing(true);

    cardList.add(new DashboardStoryCard());
    cardList.add(new DashboardStoryCard());

    this.add(cardList);
  }
}
