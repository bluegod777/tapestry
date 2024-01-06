package com.tapestry.views.common.views;

import com.tapestry.data.repositories.TapestryRepository;
import com.tapestry.security.AuthenticatedUser;
import com.tapestry.views.common.builders.HtmlLabelBuilder;
import com.tapestry.views.common.components.HtmlLabel;
import com.vaadin.flow.component.Component;

public abstract class TapestryCardSkeleton<T> extends TapestryRouterViewSkeleton<TapestryCardSkeleton<T>>
{
	private T source;

	public TapestryCardSkeleton(final T source, AuthenticatedUser authenticatedUser, final TapestryRepository repository)
	{
		super(authenticatedUser, repository);

		this.source = source;

		this.init();
	}

	public T getSource()
	{
		return this.source;
	}

	@Override
	public TapestryCardSkeleton<T> init()
	{
		this.addClassName("tapestry-card");
		this.add(this.getContent());

		return this;
	}

	public HtmlLabel getCardTitle(final String cardTitle)
	{
		HtmlLabel title = HtmlLabelBuilder.create().className("title-text").textf(cardTitle).build();

		return title;
	}

	// -------------------------------------------------------------------
	//
	// -------------------------------------------------------------------
	public abstract Component getContent();

}
