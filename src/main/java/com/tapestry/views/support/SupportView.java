package com.tapestry.views.support;

import com.tapestry.components.Container;
import com.tapestry.views.AppLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import jakarta.annotation.security.PermitAll;

@Route(value = "support", layout = AppLayout.class) @PermitAll
public class SupportView extends VerticalLayout
{

  public SupportView()
  {
    // TODO: skeleton loader
    this.render();
  }

  private void render()
  {
    Container container = new Container();

    container.add(new H1("Support"));

    container.add(new Paragraph(
        "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book."));

    this.add(container);
  }
}
