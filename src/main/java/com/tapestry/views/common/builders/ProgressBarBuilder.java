package com.tapestry.views.common.builders;

import com.vaadin.flow.component.progressbar.ProgressBar;
import com.vaadin.flow.component.progressbar.ProgressBarVariant;

public class ProgressBarBuilder
{
	private final ProgressBar progressBar;

	public static ProgressBarBuilder create()
	{
		return new ProgressBarBuilder();
	}

	public ProgressBarBuilder()
	{
		this.progressBar = new ProgressBar();
	}

	public ProgressBar build()
	{
		return this.progressBar;
	}

	public ProgressBarBuilder min(final int minValue)
	{
		this.progressBar.setMin(minValue);

		return this;
	}

	public ProgressBarBuilder max(final int maxValue)
	{
		this.progressBar.setMax(maxValue);

		return this;
	}

	public ProgressBarBuilder value(final int value)
	{
		this.progressBar.setValue(value);

		return this;
	}

	public ProgressBarBuilder barHeigh(final int height)
	{
		this.progressBar.setHeight(String.format("%dpx", height));

		return this;
	}

	public ProgressBarBuilder widthFull()
	{
		this.progressBar.setWidthFull();

		return this;
	}

	public ProgressBarBuilder success()
	{
		this.progressBar.addThemeVariants(ProgressBarVariant.LUMO_SUCCESS);

		return this;
	}

	public ProgressBarBuilder error()
	{
		this.progressBar.addThemeVariants(ProgressBarVariant.LUMO_ERROR);

		return this;
	}

	public ProgressBarBuilder contrast()
	{
		this.progressBar.addThemeVariants(ProgressBarVariant.LUMO_CONTRAST);

		return this;
	}

}
