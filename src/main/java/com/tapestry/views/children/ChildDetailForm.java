package com.tapestry.views.children;

import com.tapestry.models.entity.Entity;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.textfield.Autocomplete;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.theme.lumo.LumoUtility;

public class ChildDetailForm extends FormLayout
{
  private final Binder<Entity> binder = new Binder<>(Entity.class);

  public ChildDetailForm()
  {
    this.render();
  }

  private void render()
  {
    this.addClassNames(LumoUtility.Padding.Top.MEDIUM, LumoUtility.Padding.Bottom.MEDIUM);
    this.setResponsiveSteps(new ResponsiveStep("0", 1));

    final TextField firstNameField = new TextField();
    firstNameField.setLabel("First Name");
    firstNameField.isRequired();
    firstNameField.setAutocomplete(Autocomplete.GIVEN_NAME);
    this.binder.forField(firstNameField).asRequired().bind(Entity::getFirstName, Entity::setFirstName);

    final TextField lastNameField = new TextField();
    lastNameField.setLabel("Last Name");
    lastNameField.isRequired();
    lastNameField.setAutocomplete(Autocomplete.FAMILY_NAME);
    this.binder.forField(lastNameField).asRequired().bind(Entity::getLastName, Entity::setLastName);

    final Button submitBtn = new Button("Update", ev -> this.submitHandler());
    submitBtn.addClassNames(LumoUtility.Margin.Top.LARGE);
    submitBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

    this.add(new H2("Edit Child"));
    this.add(firstNameField, lastNameField, submitBtn);
  }

  public Binder<Entity> getBinder()
  {
    return this.binder;
  }

  private void submitHandler()
  {
    if (this.binder.validate().isOk())
    {
      // TODO: handle saving
    }
  }
}