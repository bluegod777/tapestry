package com.tapestry.views.common.views;

import com.tapestry.data.repositories.TapestryRepository;
import com.tapestry.security.AuthenticatedUser;

public abstract class TapestryViewSkeleton<S, T extends TapestryRouterViewSkeleton<?>> extends TapestryRouterViewSkeleton<T>
{
	private S source;

	// -------------------------------------------------------------------
	//
	// -------------------------------------------------------------------
	protected TapestryViewSkeleton(final S source)
	{
		super();

		this.setSource(source);
	}

	protected TapestryViewSkeleton(final S source, final AuthenticatedUser authenticatedUser, final TapestryRepository repository)
	{
		super(authenticatedUser, repository);

		this.setSource(source);
	}

	@SuppressWarnings("unchecked")
	@Override
	public T init()
	{
		return (T) this;
	}

	public S getSource()
	{
		return this.source;
	}

	public void setSource(S source)
	{
		this.source = source;
	}
}
