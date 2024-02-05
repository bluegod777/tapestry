package com.tapestry.views.onboarding;

import org.springframework.beans.factory.annotation.Autowired;

import com.tapestry.components.PhoneNumberField;
import com.tapestry.models.entity.Entity;
import com.tapestry.services.user.UserService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.textfield.Autocomplete;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.theme.lumo.LumoUtility;

public class OnboardingNewEntityForm extends FormLayout
{
	@Autowired()
	private UserService authService;

	private final Binder<Entity> binder = new Binder<>(Entity.class);

	public OnboardingNewEntityForm()
	{
		// Just do it for now
		this.setWidth("312px");
		this.setMaxWidth("312px");
		this.addClassName("align-self-center");
		this.addClassName("p-4");

		final H2 heading = new H2("New Child");
		final Paragraph subtext = new Paragraph("Get started by adding your first child");
		final Div formTitle = new Div();
		formTitle.add(heading, subtext);

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

		final EmailField emailField = new EmailField();
		emailField.setLabel("Email Address");
		emailField.setHelperText("Don't have one? Use your email instead");
		this.binder.forField(emailField).asRequired().bind(Entity::getEmailAddress, Entity::setEmailAddress);

		final PhoneNumberField phoneField = new PhoneNumberField();
		phoneField.setLabel("Mobile Phone");
		phoneField.setHelperText("Don't have one? Use your mobile number instead");
		this.binder.forField(phoneField).asRequired().bind(Entity::getMobileNumber, Entity::setMobileNumber);

		final Button submitBtn = new Button("Next", ev -> this.submitHandler());
		submitBtn.addClassNames(LumoUtility.Margin.Top.LARGE);
		submitBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

		this.add(formTitle, firstNameField, lastNameField, emailField, phoneField, submitBtn);
	}

	public Binder<Entity> getBinder()
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
			// this.authService.register(this.binder.getBean(), (error, user) ->
			// {

			// });

			// TODO: handle invalid responses, e.g. taken usernames
		}
	}
}