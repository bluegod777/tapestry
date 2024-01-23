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

	@Autowired
	UserService userService;

	private final RegistrationForm registrationForm = new RegistrationForm();

	public RegistrationView()
	{
		this.addClassName("register-view");
		this.setMinHeight("100%");
		this.setAlignItems(Alignment.CENTER);
		this.setJustifyContentMode(JustifyContentMode.CENTER);

		final Image logo = new Image("images/tapestry-logo-green.png", "Tapestry");
		logo.setWidth("250px");

		final Button loginButton = new Button("Login", ev -> this.getUI().ifPresent(ui -> ui.navigate("login")));

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
