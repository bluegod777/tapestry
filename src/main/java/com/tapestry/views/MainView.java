package com.tapestry.views;

import org.springframework.beans.factory.annotation.Autowired;

import com.tapestry.models.user.User;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.security.AuthenticationContext;

import jakarta.annotation.security.PermitAll;

@Route
@PermitAll
public class MainView extends VerticalLayout
{
	public MainView(@Autowired final AuthenticationContext authContext)
	{
		final H1 logo = new H1("Hello to Tapestry");
		logo.addClassName("logo");

		// Button click listeners can be defined as lambda expressions
		final Button button = new Button("Say hello", e ->
		{
			authContext.getAuthenticatedUser(User.class).ifPresent(details ->
			{
				this.add(new Paragraph("hello " + details.getUsername() + " " + details.getToken()));
			});

		});

		// Theme variants give you predefined extra styles for components.
		// Example: Primary button has a more prominent look.
		button.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

		// You can specify keyboard shortcuts for buttons.
		// Example: Pressing enter in this view clicks the Button.
		button.addClickShortcut(Key.ENTER);

		// Use custom CSS classes to apply styling. This is defined in
		// styles.css.
		this.addClassName("centered-content");

		this.add(button);
	}
}
