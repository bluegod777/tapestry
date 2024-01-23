package com.tapestry.views.auth;

import org.springframework.beans.factory.annotation.Autowired;

import com.tapestry.components.PhoneNumberField;
import com.tapestry.services.user.UserService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.textfield.Autocomplete;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.theme.lumo.LumoUtility;

public class LoginForm extends FormLayout
{
	@Autowired()
	private UserService authService;

	private final Binder<LoginEntity> binder = new Binder<>(LoginEntity.class);

	public LoginForm()
	{
		// Just do it for now
		this.setWidth("312px");
		this.setMaxWidth("312px");
		this.addClassName("align-self-center");
		this.addClassName("p-4");

		final H2 heading = new H2("Login");
		final Paragraph subtext = new Paragraph("Login to your Tapestry account");
		final Div formTitle = new Div();
		formTitle.add(heading, subtext);

		final PhoneNumberField phoneField = new PhoneNumberField();
		phoneField.setLabel("Mobile Phone");
		this.binder.forField(phoneField).asRequired().bind(LoginEntity::getPhone, LoginEntity::setPhone);

		final PasswordField passwordField = new PasswordField();
		passwordField.setLabel("Password");
		passwordField.setAutocomplete(Autocomplete.CURRENT_PASSWORD);
		this.binder.forField(passwordField).asRequired().bind(LoginEntity::getPassword, LoginEntity::setPassword);

		final Button submitBtn = new Button("Login", ev -> this.submitHandler());
		submitBtn.addClassNames(LumoUtility.Margin.Top.LARGE);
		submitBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

		this.add(formTitle, phoneField, passwordField, submitBtn);
	}

	public Binder<LoginEntity> getBinder()
	{
		return this.binder;
	}

	private void submitHandler()
	{
		if (this.binder.validate().isOk())
		{
			// TODO: send registration async? Or event? Or what?
			// Don't know what this is or how to get it
			// binder.getBean(), but it has the value of the form, validated
			this.authService.login(this.binder.getBean(), (error, user) ->
			{

			});

			// TODO: handle invalid passwords
		}
	}
}