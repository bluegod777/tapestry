package com.tapestry.views.auth;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

public class RegistrationForm extends FormLayout {
  private Binder<RegistrationEntity> binder;

  public RegistrationForm() {
    // Just do it
    setWidth("312px");
    setMaxWidth("312px");
    addClassName("align-self-center");
    addClassName("p-4");

    binder = new Binder<>(RegistrationEntity.class);

    H2 heading = new H2("Register");
    Paragraph subtext = new Paragraph("Create a free Tapestry account");
    Div formTitle = new Div();
    formTitle.add(heading, subtext);

    TextField nameField = new TextField();
    nameField.setLabel("First Name");
    binder.bind(nameField, RegistrationEntity::getName, RegistrationEntity::setName);

    EmailField emailField = new EmailField();
    emailField.setLabel("Email Address");
    binder.bind(emailField, RegistrationEntity::getEmail, RegistrationEntity::setEmail);

    Button submitBtn = new Button("Create Account");
    submitBtn.addClassName("mt-4");
    submitBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

    add(formTitle, nameField, emailField, submitBtn);
  }

  public Binder<RegistrationEntity> getBinder() {
    return binder;
  }
}