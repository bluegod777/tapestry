package com.tapestry.views.auth;

import org.springframework.beans.factory.annotation.Autowired;

import com.tapestry.components.PhoneNumberField;
import com.tapestry.services.AuthenticationService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.textfield.Autocomplete;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.data.binder.Binder;

public class LoginForm extends FormLayout {
  private final Binder<LoginEntity> binder = new Binder<>(LoginEntity.class);

  @Autowired()
  private AuthenticationService authService;

  public LoginForm() {
    // Just do it for now
    setWidth("312px");
    setMaxWidth("312px");
    addClassName("align-self-center");
    addClassName("p-4");

    H2 heading = new H2("Login");
    Paragraph subtext = new Paragraph("Login to your Tapestry account");
    Div formTitle = new Div();
    formTitle.add(heading, subtext);

    PhoneNumberField phoneField = new PhoneNumberField();
    phoneField.setLabel("Mobile Phone");
    binder.forField(phoneField)
        .asRequired()
        .bind(LoginEntity::getPhone, LoginEntity::setPhone);

    PasswordField passwordField = new PasswordField();
    passwordField.setLabel("Password");
    passwordField.setAutocomplete(Autocomplete.CURRENT_PASSWORD);
    binder.forField(passwordField)
        .asRequired()
        .bind(LoginEntity::getPassword, LoginEntity::setPassword);

    Button submitBtn = new Button("Login", ev -> submitHandler());
    submitBtn.addClassName("mt-4");
    submitBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

    add(formTitle, phoneField, passwordField, submitBtn);
  }

  private void submitHandler() {
    if (binder.validate().isOk()) {
      // TODO: send registration async? Or event? Or what?
      // Don't know what this is or how to get it
      // binder.getBean(), but it has the value of the form, validated
      authService.login("nick", "pw");

      // TODO: handle invalid passwords
    }
  }

  public Binder<LoginEntity> getBinder() {
    return binder;
  }
}