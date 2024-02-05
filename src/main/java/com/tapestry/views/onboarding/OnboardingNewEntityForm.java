package com.tapestry.views.onboarding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tapestry.components.PhoneNumberField;
import com.tapestry.models.entity.Entity;
import com.tapestry.services.user.UserService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.textfield.Autocomplete;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.QueryParameters;
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

		final DatePicker dateOfBirthField = new DatePicker();
		dateOfBirthField.setLabel("Date of Birth");
		this.binder.forField(dateOfBirthField).asRequired().bind((Entity::getBirthday), Entity::setBirthday);

		final Button submitBtn = new Button("Next", ev -> this.submitHandler());
		submitBtn.addClassNames(LumoUtility.Margin.Top.LARGE);
		submitBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

		this.add(formTitle, firstNameField, lastNameField, emailField, phoneField, dateOfBirthField, submitBtn);
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

			// TODO: is this really how to do this..?
			List<String> list = new ArrayList<>();
			list.add("true");

			Map<String, List<String>> parametersMap = new HashMap<String, List<String>>();
			parametersMap.put("welcome", list);
			QueryParameters qp = new QueryParameters(parametersMap);

			// navigate home with welcome params
			UI.getCurrent().navigate("/", qp);
		}
	}
}