package com.tapestry.views.children;

import com.tapestry.components.Container;
import com.tapestry.views.AppLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility;

import jakarta.annotation.security.PermitAll;

@Route(value = "child", layout = AppLayout.class) @PermitAll
public class ChildDetailView extends VerticalLayout implements HasUrlParameter<String>
{

  String entityId;

  @Override
  public void setParameter(BeforeEvent event, String parameter)
  {
    this.entityId = parameter;
    this.render();
  }

  public ChildDetailView()
  {
    // TODO: skeleton loader
  }

  private void render()
  {
    Container container = new Container();

    container.add(new H1("Child, " + this.entityId));

    container.add(new Paragraph(
        "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book."));

    Div div = new Div();
    div.addClassNames(LumoUtility.Padding.Top.MEDIUM, LumoUtility.Padding.Bottom.MEDIUM);
    div.add(new ChildDetailCurrentTbucksCard());

    container.add(div);

    container.add(new ChildDetailHistoryList());

    HorizontalLayout formLayout = new HorizontalLayout();
    formLayout.add(new ChildDetailForm());
    container.add(formLayout);

    this.add(container);
  }
}
