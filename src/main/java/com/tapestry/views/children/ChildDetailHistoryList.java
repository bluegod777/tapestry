package com.tapestry.views.children;

import com.tapestry.models.entity.Entity;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.theme.lumo.LumoUtility;

public class ChildDetailHistoryList extends VerticalLayout
{
  public ChildDetailHistoryList()
  {
    this.render();
  }

  private void render()
  {
    this.addClassNames(LumoUtility.Padding.Top.MEDIUM, LumoUtility.Padding.Bottom.MEDIUM);
    this.setPadding(false);

    Grid<Entity> grid = new Grid<>(Entity.class, false);
    grid.addColumn(Entity::getFirstName).setHeader("Action");
    grid.addColumn(Entity::getLastName).setHeader("Date");
    grid.addColumn(Entity::getRecordId).setHeader("Amount");
    grid.addColumn(Entity::getRecordId).setHeader("Type");

    // List<Person> people = DataService.getPeople();
    // grid.setItems(people);
    grid.setThemeName("no-border");

    this.add(new H2("TBucks History"));
    this.add(grid);
  }
}