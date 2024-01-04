package com.tapestry.views.common.builders;

import com.tapestry.views.common.components.HtmlLabel;

public class HtmlLabelBuilder
{
	private final HtmlLabel label;

	public static HtmlLabelBuilder create()
	{
		return new HtmlLabelBuilder();
	}

	public HtmlLabelBuilder()
	{
		this.label = new HtmlLabel();
	}

	public HtmlLabelBuilder text(final String content)
	{
		this.label.setText(content);

		return this;
	}

	public HtmlLabelBuilder textf(final String format, final Object... args)
	{
		this.label.setText(String.format(format, args));

		return this;
	}

	public HtmlLabelBuilder className(final String className)
	{
		if (className != null)
		{
			this.label.addClassName(className);
		}

		return this;
	}

	public HtmlLabelBuilder attribute(final String attributeName, final boolean state)
	{
		this.label.getElement().setAttribute(attributeName, state);

		return this;
	}

	public HtmlLabelBuilder title(final String title)
	{
		this.label.setTitle(title);

		return this;
	}

	public HtmlLabelBuilder widthFull()
	{
		this.label.setStyle("width", "100%");

		return this;
	}

	public HtmlLabelBuilder small()
	{
		this.label.setStyle("font-size", "var(--lumo-font-size-s)");

		return this;
	}

	public HtmlLabelBuilder large()
	{
		this.label.setStyle("font-size", "var(--lumo-font-size-l)");

		return this;
	}

	public HtmlLabelBuilder xlarge()
	{
		this.label.setStyle("font-size", "var(--lumo-font-size-xl)");

		return this;
	}

	public HtmlLabelBuilder center()
	{
		this.label.setStyle("text-align", "center");

		return this;
	}

	public HtmlLabelBuilder selfCenter()
	{
		this.label.setStyle("align-self", "center");

		return this;
	}

	public HtmlLabel build()
	{
		return this.label;
	}

}
