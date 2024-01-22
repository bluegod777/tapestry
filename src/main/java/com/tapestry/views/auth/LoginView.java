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
public class LoginView extends VerticalLayout implements BeforeEnterObserver {

	private final LoginForm loginForm = new LoginForm();

	@Autowired
	UserService userService;

	public LoginView() {
		addClassName("login-view");
		setMinHeight("100%");
		setAlignItems(Alignment.CENTER);
		setJustifyContentMode(JustifyContentMode.CENTER);

		Image logo = new Image("images/tapestry-logo-green.png", "Tapestry");
		logo.setWidth("250px");

		Button signupButton = new Button("No account? Sign Up Now!",
				ev -> getUI().ifPresent(ui -> ui.navigate("register")));

		add(logo, loginForm, signupButton);
	}

	@Override
	public void beforeEnter(BeforeEnterEvent event) {
		if (userService.loggedIn()) {
			event.forwardTo("");
		}
	}
}
