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
@PageTitle("Tapestry - Login")
@Route(value = "login")
public class LoginView extends VerticalLayout implements BeforeEnterObserver
{

	@Autowired
	UserService userService;

	private final LoginForm loginForm = new LoginForm();

	public LoginView()
	{
		this.addClassName("login-view");
		this.setMinHeight("100%");
		this.setAlignItems(Alignment.CENTER);
		this.setJustifyContentMode(JustifyContentMode.CENTER);

		final Image logo = new Image("images/tapestry-logo-green.png", "Tapestry");
		logo.setWidth("250px");

		final Button signupButton = new Button("No account? Sign Up Now!", ev -> this.getUI().ifPresent(ui -> ui.navigate("register")));

		this.add(logo, this.loginForm, signupButton);
	}

	@Override
	public void beforeEnter(final BeforeEnterEvent event)
	{
		this.userService.loggedIn((error, loggedIn) ->
		{
			if (!error && loggedIn != null && loggedIn.booleanValue())
			{
				event.forwardTo("");
			}
		});
	}
}
