package com.tapestry.views.common.views;

import com.tapestry.data.repositories.TapestryRepository;
import com.tapestry.security.AuthenticatedUser;
import com.tapestry.views.common.builders.HtmlLabelBuilder;

public abstract class TapestrySummaryCardSkeleton<S, T extends TapestryRouterViewSkeleton<?>> extends TapestryRouterViewSkeleton<T>
{
	private S source;

	// -------------------------------------------------------------------
	//
	// -------------------------------------------------------------------
	protected TapestrySummaryCardSkeleton(final S source)
	{
		super();

		this.setSource(source);
	}

	protected TapestrySummaryCardSkeleton(final S source, final AuthenticatedUser authenticatedUser, final TapestryRepository repository)
	{
		super(authenticatedUser, repository);

		this.setSource(source);
	}

	@SuppressWarnings("unchecked")
	@Override
	public T init()
	{
		this.addClassName("clickable-card");

		return (T) this;
	}

	@SuppressWarnings("unchecked")
	public T makeSwipable(final int height)
	{

		this.setHeight(String.format("%dpx", height));

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

	protected HtmlLabelBuilder getTitle(final String title)
	{
		return HtmlLabelBuilder.create().className("title").text(title);
	}

	protected HtmlLabelBuilder getIndicator(final String value)
	{
		return HtmlLabelBuilder.create().className("content").text(value);
	}

	protected HtmlLabelBuilder getHint(final String text)
	{
		return HtmlLabelBuilder.create().className("hints").text(text);
	}

}
