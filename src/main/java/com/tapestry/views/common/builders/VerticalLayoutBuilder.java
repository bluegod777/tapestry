package com.tapestry.views.common.builders;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class VerticalLayoutBuilder
{

	private final VerticalLayout layout;

	public static VerticalLayoutBuilder create()
	{
		return new VerticalLayoutBuilder();
	}

	public VerticalLayoutBuilder()
	{
		this.layout = new VerticalLayout();
	}

	public VerticalLayout build()
	{
		return this.layout;
	}

	public VerticalLayoutBuilder tight()
	{
		this.margin(false);
		this.padding(false);
		this.spacing(false);

		return this;
	}

	public VerticalLayoutBuilder add(final Component c)
	{
		if (c != null)
		{
			this.layout.add(c);
		}

		return this;
	}

	public VerticalLayoutBuilder add(final Component... c)
	{
		this.layout.add(c);

		return this;
	}

	public VerticalLayoutBuilder sizeFull()
	{
		this.layout.setSizeFull();

		return this;
	}

	public VerticalLayoutBuilder sizeUndefined()
	{
		this.layout.setSizeUndefined();

		return this;
	}

	public VerticalLayoutBuilder setClass(final String className)
	{
		this.layout.setClassName(className);

		return this;
	}

	public VerticalLayoutBuilder addClass(final String className)
	{
		this.layout.addClassName(className);

		return this;
	}

	public VerticalLayoutBuilder margin(final boolean m)
	{
		this.layout.setMargin(m);

		return this;
	}

	public VerticalLayoutBuilder padding(final boolean m)
	{
		this.layout.setPadding(m);

		return this;
	}

	public VerticalLayoutBuilder spacing(final boolean m)
	{
		this.layout.setSpacing(m);

		return this;
	}

	public VerticalLayoutBuilder setStyle(final String name, final String value)
	{
		this.layout.getElement().getStyle().set(name, value);

		return this;
	}

	public VerticalLayoutBuilder visible(final boolean visible)
	{
		this.layout.setVisible(visible);

		return this;
	}

	public VerticalLayoutBuilder makeScrollable()
	{
		this.setStyle("overflow-y", "auto");

		return this;
	}

	public VerticalLayoutBuilder noWrap()
	{
		this.setStyle("white-space", "nowrap");

		return this;
	}

	// -------------------------------------------------------------------
	// color support
	// -------------------------------------------------------------------
	public VerticalLayoutBuilder red()
	{
		this.setStyle("background", "red");

		return this;
	}

	public VerticalLayoutBuilder blue()
	{
		this.setStyle("background", "blue");

		return this;
	}

	public VerticalLayoutBuilder green()
	{
		this.setStyle("background", "green");

		return this;
	}

	public VerticalLayoutBuilder yellow()
	{
		this.setStyle("background", "yellow");

		return this;
	}

}
