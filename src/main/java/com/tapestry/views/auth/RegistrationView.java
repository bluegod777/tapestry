package com.tapestry.views.auth;

import org.springframework.beans.factory.annotation.Autowired;

import com.tapestry.services.user.UserService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@AnonymousAllowed
@PageTitle("Tapestry - Register")
@Route(value = "register")
public class RegistrationView extends VerticalLayout implements BeforeEnterObserver
{

	private final UserService userService;
	private final RegistrationForm registrationForm;

	public RegistrationView(@Autowired final UserService userService)
	{
		this.userService = userService;

		this.addClassName("register-view");
		this.setMinHeight("100%");
		this.setAlignItems(Alignment.CENTER);
		this.setJustifyContentMode(JustifyContentMode.CENTER);

		final Image logo = new Image("images/tapestry-logo-green.png", "Tapestry");
		logo.setWidth("405px");

		final Button loginButton = new Button("Login Instead", ev -> this.getUI().ifPresent(ui -> ui.navigate("login")));

		this.registrationForm = new RegistrationForm(this.userService);
		this.add(logo, this.registrationForm, loginButton);
	}

	@Override
	public void beforeEnter(final BeforeEnterEvent event)
	{
		this.userService.loggedIn((error, loggedIn) ->
		{
			// @TODO: Not sure what is going on
			if (!error && loggedIn != null && loggedIn.booleanValue())
			{
				event.forwardTo("");
			}
		});
	}
}
