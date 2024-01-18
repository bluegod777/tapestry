package com.tapestry.views.dashboard;

import com.tapestry.security.SecurityService;
import com.tapestry.views.MainLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

import jakarta.annotation.security.PermitAll;

@PageTitle("Tapestry")
@Route(value = "dashboard", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
@PermitAll
public class DashboardView extends VerticalLayout {
	private final SecurityService auth;

	public DashboardView(SecurityService auth) {
		this.auth = auth;
		addClassName("dashboard-view");
		setSizeFull();

		this.init();
	}

	private void init() {
		String u = auth.getAuthenticatedUser().getUsername();

		add(new H1("Hello " + u));
	}
}
