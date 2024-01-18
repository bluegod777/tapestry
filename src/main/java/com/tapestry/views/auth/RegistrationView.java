package com.tapestry.views.auth;

import org.springframework.security.access.method.P;

import com.tapestry.security.SecurityService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@AnonymousAllowed
@PageTitle("Tapestry - Register")
@Route(value = "register")
public class RegistrationView extends VerticalLayout implements BeforeEnterObserver {

	private final SecurityService auth;
	private final RegistrationForm registrationForm = new RegistrationForm();

	public RegistrationView(SecurityService auth) {
		this.auth = auth;
		addClassName("register-view");
		setSizeFull();
		setAlignItems(Alignment.CENTER);
		setJustifyContentMode(JustifyContentMode.CENTER);

		Image logo = new Image("images/tapestry-logo-green.png", "Tapestry");
		logo.setWidth("250px");

		Button loginButton = new Button("Login",
				ev -> getUI().ifPresent(ui -> ui.navigate("login")));

		add(logo, registrationForm, loginButton);
	}

	@Override
	public void beforeEnter(BeforeEnterEvent event) {
		if (auth.loggedIn()) {
			event.forwardTo("");
		}

		// this.login.setError(event.getLocation().getQueryParameters().getParameters().containsKey("error"));
	}
}
