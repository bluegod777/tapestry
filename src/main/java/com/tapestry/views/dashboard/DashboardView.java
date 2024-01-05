package com.tapestry.views.dashboard;

import com.tapestry.data.repositories.TapestryRepository;
import com.tapestry.security.AuthenticatedUser;
import com.tapestry.views.MainLayout;
import com.tapestry.views.common.views.TapestryRouterViewSkeleton;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

import jakarta.annotation.security.PermitAll;

@PageTitle("Dashboard")
@Route(value = "dashboard", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
@PermitAll
public class DashboardView extends TapestryRouterViewSkeleton<DashboardView> implements BeforeEnterObserver
{
	// -------------------------------------------------------------------
	//
	// -------------------------------------------------------------------
	public DashboardView(final AuthenticatedUser authenticatedUser, final TapestryRepository repository)
	{
		super(authenticatedUser, repository);

		this.init();
	}

	@Override
	public DashboardView init()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void beforeEnter(BeforeEnterEvent event)
	{
		// TODO Auto-generated method stub

	}

}
