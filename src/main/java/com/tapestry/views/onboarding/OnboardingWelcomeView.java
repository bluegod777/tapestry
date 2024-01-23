package com.tapestry.views.onboarding;

import com.tapestry.services.user.UserService;
import com.tapestry.components.Container;
import com.tapestry.components.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.flow.theme.lumo.LumoUtility;

@PageTitle("Welcome") @Route(value = "welcome") @AnonymousAllowed
public class OnboardingWelcomeView extends VerticalLayout
{

	public OnboardingWelcomeView(final UserService userService)
	{

		// final LoginEntity entity = new LoginEntity();
		// entity.setPhone("7758309851");
		// entity.setPassword("abcdefg");

		// userService.login(entity, (error, user) ->
		// {
		// if (error)
		// {
		// this.add(new H1("ERROR!"));
		// }
		// else
		// {
		// this.add(new H1("Welcome " + user.getUserName() + "!"));
		// }
		// });

		Container container = new Container();
		Grid grid = new Grid();
		grid.addClassName(LumoUtility.Grid.Column.COLUMNS_2);

		VerticalLayout left = new VerticalLayout();
		Div right = new Div();

		grid.add(left, right);
		container.add(grid);

		Image logo = new Image("images/tapestry-logo-green.png", "Tapestry");
		logo.setWidth("250px");

		left.add(logo);
		left.setAlignItems(Alignment.CENTER);
		left.setJustifyContentMode(JustifyContentMode.CENTER);

		//
		right.add(new H1("Form"));

		add(container);
	}
}
