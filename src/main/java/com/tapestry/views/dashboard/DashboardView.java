package com.tapestry.views.dashboard;

import org.springframework.beans.factory.annotation.Autowired;

import com.tapestry.components.Container;
import com.tapestry.models.user.User;
import com.tapestry.services.entity.EntityService;
import com.tapestry.views.AppLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.security.AuthenticationContext;

import jakarta.annotation.security.PermitAll;

@Route(value = "", layout = AppLayout.class) @PermitAll
public class DashboardView extends VerticalLayout
{

  public DashboardView(@Autowired final AuthenticationContext authContext, @Autowired final EntityService entityService)
  {

    Container container = new Container();

    // User / parent
    authContext.getAuthenticatedUser(User.class).ifPresent(details ->
    {
      entityService.getEntity(details.getUsername(), (error, entity) ->
      {
        if (error)
        {
          // In DEV no entity, work in progress
          container.add(new H1("Hey, " + details.getUsername()));
        } else
        {
          container.add(new H1("Hey, " + entity.getFirstName()));
        }
      });
    });

    container.add(new Paragraph(
        "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book."));

    // Children
    container.add(new DashboardChildCardList());

    // Tbucks
    container.add(new DashboardStoryCardList());

    this.add(container);
  }
}
