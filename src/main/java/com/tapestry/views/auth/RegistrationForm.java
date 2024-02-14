package com.tapestry.views.auth;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.tapestry.components.PhoneNumberField;
import com.tapestry.services.user.UserService;
import com.vaadin.flow.component.UI;
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

public class RegistrationForm extends FormLayout
{
	private final Binder<RegistrationEntity> binder;
	private final UserService userService;
	private final RegistrationEntity entity = new RegistrationEntity();

	public RegistrationForm(@Autowired() final UserService userService)
	{
		// NOTE: do we really have to do this every time?
		this.userService = userService;

		// Just do it for now
		this.setWidth("355px");
		this.setMaxWidth("355px");
		this.addClassNames("align-self-center", "p-4");

		final H2 heading = new H2("Register");
		final Paragraph subtext = new Paragraph("Create a free Tapestry account");
		final Div formTitle = new Div();
		formTitle.add(heading, subtext);

		this.binder = new Binder<RegistrationEntity>(RegistrationEntity.class);
		binder.setBean(this.entity);

		final TextField firstNameField = new TextField();
		firstNameField.setLabel("First Name");
		firstNameField.isRequired();
		firstNameField.setAutocomplete(Autocomplete.GIVEN_NAME);
		this.binder.forField(firstNameField).asRequired().bind("firstName");

		final TextField lastNameField = new TextField();
		lastNameField.setLabel("Last Name");
		lastNameField.isRequired();
		lastNameField.setAutocomplete(Autocomplete.FAMILY_NAME);
		this.binder.forField(lastNameField).asRequired().bind("lastName");

		final EmailField emailField = new EmailField();
		emailField.setLabel("Email Address");
		emailField.setAutocomplete(Autocomplete.EMAIL);
		this.binder.forField(emailField).asRequired().bind("email");

		final PhoneNumberField phoneField = new PhoneNumberField();
		phoneField.setLabel("Mobile Phone");
		phoneField.setAutocomplete(Autocomplete.TEL);
		phoneField.setHelperText("We'll use this to verify your account");
		this.binder.forField(phoneField).asRequired().bind(RegistrationEntity::getPhone, RegistrationEntity::setPhone);

		final PasswordField passwordField = new PasswordField();
		passwordField.setLabel("Password");
		passwordField.setAutocomplete(Autocomplete.NEW_PASSWORD);
		this.binder.forField(passwordField).asRequired().bind("password");

		final Button submitBtn = new Button("Create Account", ev -> this.submitHandler());
		submitBtn.addClassNames(LumoUtility.Margin.Top.LARGE);
		submitBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

		this.add(formTitle, firstNameField, lastNameField, emailField, phoneField, passwordField, submitBtn);
	}

	public Binder<RegistrationEntity> getBinder()
	{
		return this.binder;
	}

	public Optional<RegistrationEntity> getBean()
	{
		return Optional.ofNullable(this.getBinder().getBean());
	}

	private void submitHandler()
	{
		if (this.getBinder().validate().hasErrors())
		{
			// TODO: handle validation
			System.err.println("Invalid");
			return;
		}

		Optional<RegistrationEntity> optional = this.getBean();

		if (optional.isPresent())
		{

			this.userService.register(this.entity, (error, user) ->
			{
				if (error)
				{
					// TODO: handle errors
					System.err.format("Form error {}", error);
				} else
				{
					UI.getCurrent().navigate("/welcome");
				}
			});
		} else
		{
			System.err.println("Not present, what does that mean");
		}
	}
}