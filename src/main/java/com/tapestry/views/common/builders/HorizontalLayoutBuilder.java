package com.tapestry.views.common.builders;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

public class HorizontalLayoutBuilder
{

	private final HorizontalLayout layout;

	public static HorizontalLayoutBuilder create()
	{
		return new HorizontalLayoutBuilder();
	}

	public HorizontalLayoutBuilder()
	{
		this.layout = new HorizontalLayout();
		this.layout.setWidth("100%");
	}

	public HorizontalLayout build()
	{
		return this.layout;
	}

	public HorizontalLayoutBuilder tight()
	{
		this.margin(false);
		this.padding(false);
		this.spacing(false);

		return this;
	}

	public HorizontalLayoutBuilder add(final Component c)
	{
		if (c != null)
		{
			this.layout.add(c);
		}

		return this;
	}

	public HorizontalLayoutBuilder add(final Component... components)
	{
		for (Component c : components)
		{
			this.add(c);
		}

		return this;
	}

	public HorizontalLayoutBuilder sizeUndefined()
	{
		this.layout.setSizeUndefined();

		return this;
	}

	public HorizontalLayoutBuilder sizeFull()
	{
		this.layout.setSizeFull();

		return this;
	}

	public HorizontalLayoutBuilder widthFull()
	{
		this.layout.setWidthFull();

		return this;
	}

	public HorizontalLayoutBuilder heightFull()
	{
		this.layout.setHeightFull();

		return this;
	}

	public HorizontalLayoutBuilder setClass(final String className)
	{
		this.layout.setClassName(className);

		return this;
	}

	public HorizontalLayoutBuilder addClass(final String className)
	{
		this.layout.addClassName(className);

		return this;
	}

	public HorizontalLayoutBuilder margin(final boolean m)
	{
		this.layout.setMargin(m);

		return this;
	}

	public HorizontalLayoutBuilder padding(final boolean m)
	{
		this.layout.setPadding(m);

		return this;
	}

	public HorizontalLayoutBuilder spacing(final boolean m)
	{
		this.layout.setSpacing(m);

		return this;
	}

	public HorizontalLayoutBuilder setStyle(final String name, final String value)
	{
		this.layout.getElement().getStyle().set(name, value);

		return this;
	}

	public HorizontalLayoutBuilder visible(final boolean visible)
	{
		this.layout.setVisible(visible);

		return this;
	}

	public HorizontalLayoutBuilder makeScrollable()
	{
		this.setStyle("overflow-y", "auto");

		return this;
	}

	public HorizontalLayoutBuilder center()
	{
		this.setStyle("align-self", "center");

		return this;
	}

	// -------------------------------------------------------------------
	// color support
	// -------------------------------------------------------------------
	public HorizontalLayoutBuilder red()
	{
		this.setStyle("background", "red");

		return this;
	}

	public HorizontalLayoutBuilder blue()
	{
		this.setStyle("background", "blue");

		return this;
	}

	public HorizontalLayoutBuilder green()
	{
		this.setStyle("background", "green");

		return this;
	}

	public HorizontalLayoutBuilder yellow()
	{
		this.setStyle("background", "yellow");

		return this;
	}

}
