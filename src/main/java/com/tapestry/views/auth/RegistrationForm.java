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
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.theme.lumo.LumoUtility;

public class RegistrationForm extends FormLayout {
  private final Binder<RegistrationEntity> binder = new Binder<>(RegistrationEntity.class);

  @Autowired()
  private AuthenticationService authService;

  public RegistrationForm() {
    // Just do it for now
    setWidth("312px");
    setMaxWidth("312px");
    addClassName("align-self-center");
    addClassName("p-4");

    H2 heading = new H2("Register");
    Paragraph subtext = new Paragraph("Create a free Tapestry account");
    Div formTitle = new Div();
    formTitle.add(heading, subtext);

    TextField firstNameField = new TextField();
    firstNameField.setLabel("First Name");
    firstNameField.isRequired();
    firstNameField.setAutocomplete(Autocomplete.GIVEN_NAME);
    binder.forField(firstNameField)
        .asRequired()
        .bind(RegistrationEntity::getFirstName, RegistrationEntity::setFirstName);

    TextField lastNameField = new TextField();
    lastNameField.setLabel("Last Name");
    lastNameField.isRequired();
    lastNameField.setAutocomplete(Autocomplete.FAMILY_NAME);
    binder.forField(lastNameField)
        .asRequired()
        .bind(RegistrationEntity::getLastName, RegistrationEntity::setLastName);

    EmailField emailField = new EmailField();
    emailField.setLabel("Email Address");
    binder.forField(emailField)
        .asRequired()
        .bind(RegistrationEntity::getEmail, RegistrationEntity::setEmail);

    PhoneNumberField phoneField = new PhoneNumberField();
    phoneField.setLabel("Mobile Phone");
    phoneField.setHelperText("We'll use this to verify your account");
    binder.forField(phoneField)
        .asRequired()
        .bind(RegistrationEntity::getPhone, RegistrationEntity::setPhone);

    PasswordField passwordField = new PasswordField();
    passwordField.setLabel("Password");
    passwordField.setAutocomplete(Autocomplete.NEW_PASSWORD);
    binder.forField(passwordField)
        .asRequired()
        .bind(RegistrationEntity::getPassword, RegistrationEntity::setPassword);

    Button submitBtn = new Button("Create Account", ev -> submitHandler());
    submitBtn.addClassNames(LumoUtility.Margin.Top.LARGE);
    submitBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

    add(formTitle, firstNameField, lastNameField, emailField, phoneField, passwordField, submitBtn);
  }

  private void submitHandler() {
    if (binder.validate().isOk()) {
      // TODO: send registration async? Or event? Or what?
      // Don't know what this is or how to get it
      // binder.getBean(), but it has the value of the form, validated
      authService.register(binder.getBean());

      // TODO: handle invalid responses, e.g. taken usernames
    }
  }

  public Binder<RegistrationEntity> getBinder() {
    return binder;
  }
}