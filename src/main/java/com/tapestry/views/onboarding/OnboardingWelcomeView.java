package com.tapestry.views.onboarding;

import com.tapestry.services.user.UserService;
import com.tapestry.views.auth.LoginEntity;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@PageTitle("Welcome")
@Route(value = "welcome")
@AnonymousAllowed
public class OnboardingWelcomeView extends VerticalLayout
{

	public OnboardingWelcomeView(final UserService userService)
	{
		// TODO: @fbarrie-knowtal this is null?
		// userService.getCurrentUser();

		final LoginEntity entity = new LoginEntity();
		entity.setPhone("7758309851");
		entity.setPassword("abcdefg");

		userService.login(entity, (error, user) ->
		{
			if (error)
			{
				this.add(new H1("ERROR!"));
			}
			else
			{
				this.add(new H1("Welcome " + user.getUserName() + "!"));
			}
		});
	}
}
