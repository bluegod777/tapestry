package com.tapestry.views.common.builders;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.orderedlayout.FlexLayout;

public class FlexLayoutBuilder
{
	private final FlexLayout layout;

	public static FlexLayoutBuilder create()
	{
		return new FlexLayoutBuilder();
	}

	public FlexLayoutBuilder()
	{
		this.layout = new FlexLayout();
	}

	public FlexLayout build()
	{
		return this.layout;
	}

	public FlexLayoutBuilder add(final Component c)
	{
		if (c != null)
		{
			this.layout.add(c);
		}

		return this;
	}

	public FlexLayoutBuilder add(final Component... c)
	{
		for (Component ca : c)
		{
			if (ca != null)
			{
				this.add(ca);
			}
		}

		return this;
	}

	public FlexLayoutBuilder sizeFull()
	{
		this.layout.setSizeFull();

		return this;
	}

	public FlexLayoutBuilder widthFull()
	{
		this.layout.setWidthFull();

		return this;
	}

	public FlexLayoutBuilder sizeUndefined()
	{
		this.layout.setSizeUndefined();

		return this;
	}

	public FlexLayoutBuilder setClass(final String className)
	{
		this.layout.setClassName(className);

		return this;
	}

	public FlexLayoutBuilder addClass(final String className)
	{
		this.layout.addClassName(className);

		return this;
	}

	public FlexLayoutBuilder setStyle(final String name, final String value)
	{
		this.layout.getElement().getStyle().set(name, value);

		return this;
	}

	public FlexLayoutBuilder justifyContentMode(final JustifyContentMode mode)
	{
		this.layout.setJustifyContentMode(mode);

		return this;
	}

	public FlexLayoutBuilder between()
	{
		this.justifyContentMode(JustifyContentMode.BETWEEN);

		return this;
	}

	public FlexLayoutBuilder around()
	{
		this.justifyContentMode(JustifyContentMode.AROUND);

		return this;
	}

	public FlexLayoutBuilder center()
	{
		this.justifyContentMode(JustifyContentMode.CENTER);

		return this;
	}

	public FlexLayoutBuilder end()
	{
		this.justifyContentMode(JustifyContentMode.END);

		return this;
	}

	public FlexLayoutBuilder start()
	{
		this.justifyContentMode(JustifyContentMode.START);

		return this;
	}

	public FlexLayoutBuilder evenly()
	{
		this.justifyContentMode(JustifyContentMode.EVENLY);

		return this;
	}

	// -------------------------------------------------------------------
	// color support
	// -------------------------------------------------------------------
	public FlexLayoutBuilder red()
	{
		this.setStyle("background", "red");

		return this;
	}

	public FlexLayoutBuilder blue()
	{
		this.setStyle("background", "blue");

		return this;
	}

	public FlexLayoutBuilder green()
	{
		this.setStyle("background", "green");

		return this;
	}

	public FlexLayoutBuilder yellow()
	{
		this.setStyle("background", "yellow");

		return this;
	}

}
