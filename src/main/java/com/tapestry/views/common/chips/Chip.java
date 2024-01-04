/**
 *
 */
package com.tapestry.views.common.chips;

import com.tapestry.views.common.builders.HtmlLabelBuilder;
import com.tapestry.views.common.builders.ImageBuilder;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

public class Chip extends HorizontalLayout
{

	public static final String CLASS_NAME = "chip";

	public static final String PRIMARY = "primary";
	public static final String PRIMARY_LIGHTER = "primary-lighter";
	public static final String SUCCESS = "success";
	public static final String ERROR = "error";
	public static final String WARNING = "warning";

	private String initialAttribute = "";

	// -------------------------------------------------------------------
	//
	// -------------------------------------------------------------------
	public static Chip create()
	{
		return new Chip();
	}

	public Chip()
	{
		this.addClassName(Chip.CLASS_NAME);

		this.init();
	}

	public void init()
	{
		this.setSpacing(true);
	}

	public Chip success()
	{
		return this.success(true);
	}

	public Chip success(final boolean value)
	{
		this.initialAttribute = Chip.SUCCESS;

		this.getElement().setAttribute(Chip.SUCCESS, value);

		return this;
	}

	public Chip primary()
	{
		return this.primary(true);
	}

	public Chip primary(final boolean value)
	{
		this.initialAttribute = Chip.PRIMARY;

		this.getElement().setAttribute(Chip.PRIMARY, value);

		return this;
	}

	public Chip primaryLighter()
	{
		return this.primaryLighter(true);
	}

	public Chip primaryLighter(final boolean value)
	{
		this.initialAttribute = Chip.PRIMARY_LIGHTER;

		this.getElement().setAttribute(Chip.PRIMARY, value);

		return this;
	}

	public Chip error()
	{
		return this.error(true);
	}

	public Chip error(final boolean value)
	{
		this.initialAttribute = Chip.ERROR;

		this.getElement().setAttribute(Chip.ERROR, value);

		return this;
	}

	public Chip warning()
	{
		return this.warning(true);
	}

	public Chip warning(final boolean value)
	{
		this.initialAttribute = Chip.WARNING;

		this.getElement().setAttribute(Chip.WARNING, value);

		return this;
	}

	public Chip icon(final VaadinIcon icon)
	{
		this.clear();

		this.add(icon.create());

		return this;
	}

	public Chip image(final String imageUrl)
	{
		this.clear();

		this.add(ImageBuilder.create().url(imageUrl, "").build());

		return this;
	}

	public Chip text(final String text)
	{
		this.clear();

		this.add(HtmlLabelBuilder.create().text(text).className("text").build());

		return this;
	}

	public Chip clearAttributes()
	{
		this.success(false);
		this.error(false);
		this.warning(false);

		return this;
	}

	public Chip clear()
	{
		this.clearAttributes();

		this.removeAll();

		return this;
	}

	public String getInitialAttribute()
	{
		return this.initialAttribute;
	}

	public void setInitialAttribute(final String initialAttribute)
	{
		this.initialAttribute = initialAttribute;
	}

	public Chip clickListener(final ComponentEventListener<ClickEvent<HorizontalLayout>> listener)
	{
		this.addClickListener(listener);

		return this;
	}

}
