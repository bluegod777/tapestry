package com.tapestry.views.dashboard;

import org.springframework.beans.factory.annotation.Autowired;

import com.tapestry.components.Container;
import com.tapestry.models.user.User;
import com.tapestry.views.AppLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.security.AuthenticationContext;
import com.vaadin.flow.theme.lumo.LumoUtility;

import jakarta.annotation.security.PermitAll;

@Route(value = "", layout = AppLayout.class) @PermitAll
public class DashboardView extends VerticalLayout
{
  public DashboardView(@Autowired final AuthenticationContext authContext)
  {

    Container container = new Container();

    // User / parent
    authContext.getAuthenticatedUser(User.class).ifPresent(details ->
    {
      container.add(new H1("Hey, " + details.getUsername()));
    });

    container.add(new Paragraph(
        "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book."));

    // Children
    Div childrenElm = new Div();
    childrenElm.setClassName(LumoUtility.Margin.Top.LARGE);

    H2 childrenTitle = new H2("Children");

    childrenElm.add(childrenTitle);
    container.add(childrenElm);

    // Tapestry Bucks
    Div tbucksElm = new Div();
    tbucksElm.setClassName(LumoUtility.Margin.Top.LARGE);

    H2 tbucksElmTitle = new H2("Tapestry Bucks");

    tbucksElm.add(tbucksElmTitle);
    container.add(tbucksElm);

    this.add(container);
  }
}
