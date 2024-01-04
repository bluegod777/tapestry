package com.tapestry.views.common.builders;

import com.vaadin.flow.component.HtmlContainer;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.H5;
import com.vaadin.flow.component.html.H6;

public class HBuilder
{
	private HtmlContainer text;

	public static HBuilder create()
	{
		HBuilder b = new HBuilder();

		return b;
	}

	public HBuilder h1()
	{
		this.text = new H1();

		return this;
	}

	public HBuilder h2()
	{
		this.text = new H2();

		return this;
	}

	public HBuilder h3()
	{
		this.text = new H3();

		return this;
	}

	public HBuilder h4()
	{
		this.text = new H4();

		return this;
	}

	public HBuilder h5()
	{
		this.text = new H5();

		return this;
	}

	public HBuilder h6()
	{
		this.text = new H6();

		return this;
	}

	public HtmlContainer build()
	{
		return this.text;
	}

	public HBuilder text(final String text)
	{
		this.text.setText(text);

		return this;
	}

	public HBuilder width(final String width)
	{
		this.text.setWidth(width);

		return this;
	}

	public HBuilder widthFull()
	{
		this.text.setWidthFull();

		return this;
	}

	public HBuilder heightFull()
	{
		this.text.setHeightFull();

		return this;
	}

	public HBuilder nowrap()
	{
		return this.setStyle("white-space", "nowrap");
	}

	public HBuilder className(final String className)
	{
		this.text.setClassName(className);

		return this;
	}

	public HBuilder size(final int fontSize)
	{
		return this.setStyle("font-size", String.format("%dpx", fontSize));
	}

	public HBuilder small()
	{
		return this.setStyle("font-size", "var(--lumo-font-size-s)");
	}

	public HBuilder large()
	{
		return this.setStyle("font-size", "var(--lumo-font-size-l)");
	}

	public HBuilder xlarge()
	{
		return this.setStyle("font-size", "var(--lumo-font-size-xl)");
	}

	public HBuilder center()
	{
		return this.setStyle("text-align", "center");
	}

	public HBuilder selfCenter()
	{
		return this.setStyle("align-self", "center");
	}

	public HBuilder paddingSmall()
	{
		return this.setStyle("padding", "var(--lumo-space-s)");
	}

	public HBuilder right()
	{
		return this.setStyle("text-align", "right");
	}

	public HBuilder color(final String color)
	{
		return this.setStyle("color", color);
	}

	public HBuilder setStyle(final String name, final String value)
	{
		this.text.getStyle().set(name, value);

		return this;
	}

	public HBuilder indentLeft()
	{
		this.setStyle("padding-left", "var(--lumo-space-s");

		return this;
	}

	public HBuilder header()
	{
		this.text.getStyle().set("color", "var(--lumo-header-text-color)");

		return this;
	}

	public HBuilder body()
	{
		this.text.getStyle().set("color", "var(--lumo-body-text-color)");

		return this;
	}

	public HBuilder secondary()
	{
		this.text.getStyle().set("color", "var(--lumo-secondary-text-color)");

		return this;
	}

	public HBuilder tertirary()
	{
		this.text.getStyle().set("color", "var(--lumo-tertirary-text-color)");

		return this;
	}

	public HBuilder disabled()
	{
		this.text.getStyle().set("color", "var(--lumo-disabled-text-color)");

		return this;
	}

	public HBuilder primary()
	{
		this.text.getStyle().set("color", "var(--lumo-primary-text-color)");

		return this;
	}

	public HBuilder error()
	{
		this.text.getStyle().set("color", "var(--lumo-error-text-color)");

		return this;
	}

	public HBuilder success()
	{
		this.text.getStyle().set("color", "var(--lumo-success-text-color)");

		return this;
	}

	public HBuilder link()
	{
		this.text.getStyle().set("color", "var(--lumo-link-text-color)");

		return this;
	}

	public HBuilder italic()
	{
		this.text.getStyle().set("font-style", "italic");

		return this;
	}

	public HBuilder bold()
	{
		this.text.getStyle().set("font-weight", "900");

		return this;
	}

	public HBuilder background(final String color)
	{
		this.text.getStyle().set("background", color);

		return this;
	}

}
