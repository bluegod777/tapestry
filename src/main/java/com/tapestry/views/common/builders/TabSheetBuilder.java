package com.tapestry.views.common.builders;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.tabs.TabSheet;

public class TabSheetBuilder
{

	private final TabSheet field;

	public static TabSheetBuilder create()
	{
		return new TabSheetBuilder();
	}

	public TabSheetBuilder()
	{
		this.field = new TabSheet();
	}

	public TabSheet build()
	{
		return this.field;
	}

	public TabSheetBuilder widthFull()
	{
		this.field.setWidthFull();

		return this;
	}

	public TabSheetBuilder heightFull()
	{
		this.field.setHeightFull();

		return this;
	}

	public TabSheetBuilder className(final String className)
	{
		this.field.setClassName(className);

		return this;
	}

	public TabSheetBuilder size(final int fontSize)
	{
		return this.setStyle("font-size", String.format("%dpx", fontSize));
	}

	public TabSheetBuilder center()
	{
		return this.setStyle("text-align", "center");
	}

	public TabSheetBuilder selfCenter()
	{
		return this.setStyle("align-self", "center");
	}

	public TabSheetBuilder paddingSmall()
	{
		return this.setStyle("padding", "var(--lumo-space-s)");
	}

	public TabSheetBuilder paddingxSmall()
	{
		return this.setStyle("padding", "var(--lumo-space-xs)");
	}

	public TabSheetBuilder paddingxxSmall()
	{
		return this.setStyle("padding", "var(--lumo-space-xxs)");
	}

	public TabSheetBuilder right()
	{
		return this.setStyle("text-align", "right");
	}

	public TabSheetBuilder color(final String color)
	{
		return this.setStyle("color", color);
	}

	public TabSheetBuilder setStyle(final String name, final String value)
	{
		this.field.getStyle().set(name, value);

		return this;
	}

	public TabSheetBuilder prefix(final VaadinIcon icon)
	{
		this.field.setPrefixComponent(icon.create());

		return this;
	}

	public TabSheetBuilder prefixIcon(final VaadinIcon icon)
	{
		this.field.setPrefixComponent(icon.create());

		return this;
	}

	public TabSheetBuilder addTab(final String tabName, final Component content)
	{
		this.field.add(tabName, content);

		return this;
	}
}
