package com.tapestry.views.settings;

import com.tapestry.data.repositories.TapestryRepository;
import com.tapestry.security.AuthenticatedUser;
import com.tapestry.views.MainLayout;
import com.tapestry.views.common.views.TapestryRouterViewSkeleton;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import jakarta.annotation.security.PermitAll;

@PageTitle("Settings")
@Route(value = "settings", layout = MainLayout.class)
@PermitAll
public class SettingsView extends TapestryRouterViewSkeleton<SettingsView> implements BeforeEnterObserver
{
	// -------------------------------------------------------------------
	//
	// -------------------------------------------------------------------
	public SettingsView(final AuthenticatedUser authenticatedUser, final TapestryRepository repository)
	{
		super(authenticatedUser, repository);

		this.init();
	}

	@Override
	public SettingsView init()
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