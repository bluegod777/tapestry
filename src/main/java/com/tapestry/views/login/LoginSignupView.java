package com.tapestry.views.login;

import com.tapestry.security.AuthenticatedUser;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@Route("login")
@PageTitle("Login | Tapestry Login")
@AnonymousAllowed
public class LoginSignupView extends VerticalLayout
{
	public LoginSignupView(final AuthenticatedUser authenticatedUser)
	{
		this.add(new Button("Hey!"));
	}
}
