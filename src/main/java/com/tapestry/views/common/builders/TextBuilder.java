package com.tapestry.views.common.builders;

import com.vaadin.flow.component.Text;

public class TextBuilder
{

	private final Text text;

	public static TextBuilder create()
	{
		return new TextBuilder();
	}

	public TextBuilder()
	{
		this.text = new Text("");
	}

	public Text build()
	{
		return this.text;
	}

	public TextBuilder text(final String text)
	{
		this.text.setText(text);

		return this;
	}

	public TextBuilder textf(final String format, final Object... args)
	{
		this.text.setText(String.format(format, args));

		return this;
	}

	public TextBuilder width(final String width)
	{
		return this;
	}

	public TextBuilder widthFull()
	{
		return this;
	}

	public TextBuilder heightFull()
	{
		return this;
	}

	public TextBuilder nowrap()
	{
		return this.setStyle("white-space", "nowrap");
	}

	public TextBuilder className(final String className)
	{
		this.text.setClassName(className);

		return this;
	}

	public TextBuilder size(final int fontSize)
	{
		return this.setStyle("font-size", String.format("%dpx", fontSize));
	}

	public TextBuilder small()
	{
		return this.setStyle("font-size", "var(--lumo-font-size-s)");
	}

	public TextBuilder large()
	{
		return this.setStyle("font-size", "var(--lumo-font-size-l)");
	}

	public TextBuilder xlarge()
	{
		return this.setStyle("font-size", "var(--lumo-font-size-xl)");
	}

	public TextBuilder center()
	{
		return this.setStyle("text-align", "center");
	}

	public TextBuilder selfCenter()
	{
		return this.setStyle("align-self", "center");
	}

	public TextBuilder paddingSmall()
	{
		return this.setStyle("padding", "var(--lumo-space-s)");
	}

	public TextBuilder right()
	{
		return this.setStyle("text-align", "right");
	}

	public TextBuilder color(final String color)
	{
		return this.setStyle("color", color);
	}

	public TextBuilder setStyle(final String name, final String value)
	{
		this.text.getStyle().set(name, value);

		return this;
	}

	public TextBuilder indentLeft()
	{
		this.setStyle("padding-left", "var(--lumo-space-s");

		return this;
	}

	public TextBuilder header()
	{
		this.text.getStyle().set("color", "var(--lumo-header-text-color)");

		return this;
	}

	public TextBuilder body()
	{
		this.text.getStyle().set("color", "var(--lumo-body-text-color)");

		return this;
	}

	public TextBuilder secondary()
	{
		this.text.getStyle().set("color", "var(--lumo-secondary-text-color)");

		return this;
	}

	public TextBuilder tertirary()
	{
		this.text.getStyle().set("color", "var(--lumo-tertirary-text-color)");

		return this;
	}

	public TextBuilder disabled()
	{
		this.text.getStyle().set("color", "var(--lumo-disabled-text-color)");

		return this;
	}

	public TextBuilder primary()
	{
		this.text.getStyle().set("color", "var(--lumo-primary-text-color)");

		return this;
	}

	public TextBuilder error()
	{
		this.text.getStyle().set("color", "var(--lumo-error-text-color)");

		return this;
	}

	public TextBuilder success()
	{
		this.text.getStyle().set("color", "var(--lumo-success-text-color)");

		return this;
	}

	public TextBuilder link()
	{
		this.text.getStyle().set("color", "var(--lumo-link-text-color)");

		return this;
	}

	public TextBuilder italic()
	{
		this.text.getStyle().set("font-style", "italic");

		return this;
	}

	public TextBuilder bold()
	{
		this.text.getStyle().set("font-weight", "900");

		return this;
	}

	public TextBuilder background(final String color)
	{
		this.text.getStyle().set("background", color);

		return this;
	}

}
