package com.tapestry.views.auth;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.login.LoginI18n.Form;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@AnonymousAllowed @PageTitle("Tapestry - Login") @Route(value = "login")
public class LoginView extends VerticalLayout implements BeforeEnterObserver
{

	private final LoginForm loginForm;

	public LoginView()
	{
		final LoginI18n i18n = LoginI18n.createDefault();
		final Form form = i18n.getForm();
		form.setTitle("Login");
		form.setUsername("Mobile phone");
		form.setSubmit("Login");

		this.loginForm = new LoginForm(i18n);
		this.loginForm.setAction("login");

		this.addClassName("login-view");
		this.setMinHeight("100%");
		this.setAlignItems(Alignment.CENTER);
		this.setJustifyContentMode(JustifyContentMode.CENTER);

		final Image logo = new Image("images/tapestry-logo-green.png", "Tapestry");
		logo.setWidth("250px");

		final Button signupButton = new Button("No account? Sign Up Now!",
				ev -> this.getUI().ifPresent(ui -> ui.navigate("register")));

		this.add(logo, this.loginForm, signupButton);
	}

	@Override
	public void beforeEnter(final BeforeEnterEvent beforeEnterEvent)
	{
		if (beforeEnterEvent.getLocation().getQueryParameters().getParameters().containsKey("error"))
		{
			this.loginForm.setError(true);
		}
	}
}
