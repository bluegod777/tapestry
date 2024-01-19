package com.tapestry.views.common.views;

import com.tapestry.data.entity.UserOld;
import com.tapestry.data.repositories.TapestryRepository;
import com.tapestry.security.AuthenticatedUser;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public abstract class TapestryRouterViewSkeleton<T extends TapestryRouterViewSkeleton<?>> extends VerticalLayout implements ITapestryView
{
	private final AuthenticatedUser authenticatedUser;
	private final TapestryRepository repository;

	// -------------------------------------------------------------------
	//
	// -------------------------------------------------------------------
	protected TapestryRouterViewSkeleton()
	{
		this.authenticatedUser = null;
		this.repository = null;
	}

	protected TapestryRouterViewSkeleton(final AuthenticatedUser authenticatedUser, final TapestryRepository repository)
	{
		this.authenticatedUser = authenticatedUser;
		this.repository = repository;
	}

	@SuppressWarnings("unchecked")
	protected T makeTight()
	{
		this.setSpacing(false);
		this.setMargin(false);
		this.setPadding(false);

		return (T) this;
	}

	public AuthenticatedUser getAuthenticatedUser()
	{
		return this.authenticatedUser;
	}

	// -------------------------------------------------------------------
	//
	// -------------------------------------------------------------------
	public TapestryRepository getRepository()
	{
		return this.repository;
	}

	// -------------------------------------------------------------------
	// Some common things we do with the user
	// -------------------------------------------------------------------
	@Override
	public UserOld getTapestryUser()
	{
		return this.getAuthenticatedUser().get().get();
	}

	protected String getUserFullName()
	{
		return String.format("%s %s", this.getTapestryUser().getFirstName(), this.getTapestryUser().getLastName());
	}

	protected String getUserMailingAddress()
	{
		return String.format("%s", "Not supported just yet.<br>Need to add some tables.");
	}

	protected String getUserTapTag()
	{
		return String.format("$%s%s", this.getTapestryUser().getFirstName(), this.getTapestryUser().getLastName());
	}

	protected String getUserEmailAddresses()
	{
		return String.format("%s", "omey@somewhere.com<br>omey@otherwhere.com");
	}

	protected String getUserMobilePhoneNumbers()
	{
		return String.format("%s", "(555) 555-5555<br>(360) 555-5555");
	}

	// -------------------------------------------------------------------
	// Our true abstracts
	// -------------------------------------------------------------------
	public abstract T init();
}
