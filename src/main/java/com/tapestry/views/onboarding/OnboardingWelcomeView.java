package com.tapestry.views.onboarding;

import com.tapestry.components.Container;
import com.tapestry.components.Grid;
import com.tapestry.services.user.UserService;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility;

import jakarta.annotation.security.PermitAll;

@PageTitle("Welcome") @Route(value = "welcome") @PermitAll
public class OnboardingWelcomeView extends VerticalLayout
{

	public OnboardingWelcomeView(final UserService userService)
	{

		this.setAlignItems(Alignment.CENTER);
		this.setJustifyContentMode(JustifyContentMode.CENTER);
		this.setMinHeight("100%");

		Container container = new Container();
		Grid grid = new Grid();
		grid.addClassName(LumoUtility.Grid.Column.COLUMNS_2);

		VerticalLayout left = new VerticalLayout();
		Div right = new Div();

		grid.add(left, right);
		container.add(grid);

		Image logo = new Image("images/tapestry-logo-green.png", "Tapestry");
		logo.setWidth("250px");
		H1 title = new H1("Welcome to Tapestry");
		Paragraph intro = new Paragraph("The first finance app that makes finance make sense");

		left.add(logo, title, intro);

		left.setAlignItems(Alignment.CENTER);
		left.setJustifyContentMode(JustifyContentMode.CENTER);

		right.add(new OnboardingNewEntityForm());

		add(container);
	}
}
