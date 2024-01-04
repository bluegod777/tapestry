package com.tapestry.views.common.builders;

import com.vaadin.flow.component.AbstractField.ComponentValueChangeEvent;
import com.vaadin.flow.component.HasValue;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;

public class TextFieldBuilder
{

	private final TextField field;

	public static TextFieldBuilder create()
	{
		return new TextFieldBuilder();
	}

	public TextFieldBuilder()
	{
		this.field = new TextField();
	}

	public TextField build()
	{
		return this.field;
	}

	public TextFieldBuilder label(final String label)
	{
		this.field.setLabel(label);

		return this;
	}

	public TextFieldBuilder value(final String value)
	{
		this.field.setValue(value);

		return this;
	}

	public TextFieldBuilder widthFull()
	{
		this.field.setWidthFull();

		return this;
	}

	public TextFieldBuilder heightFull()
	{
		this.field.setHeightFull();

		return this;
	}

	public TextFieldBuilder className(final String className)
	{
		this.field.setClassName(className);

		return this;
	}

	public TextFieldBuilder size(final int fontSize)
	{
		return this.setStyle("font-size", String.format("%dpx", fontSize));
	}

	public TextFieldBuilder required()
	{
		this.field.setRequired(true);
		this.field.setRequiredIndicatorVisible(true);

		return this;
	}

	public TextFieldBuilder center()
	{
		return this.setStyle("text-align", "center");
	}

	public TextFieldBuilder selfCenter()
	{
		return this.setStyle("align-self", "center");
	}

	public TextFieldBuilder paddingSmall()
	{
		return this.setStyle("padding", "var(--lumo-space-s)");
	}

	public TextFieldBuilder paddingxSmall()
	{
		return this.setStyle("padding", "var(--lumo-space-xs)");
	}

	public TextFieldBuilder paddingxxSmall()
	{
		return this.setStyle("padding", "var(--lumo-space-xxs)");
	}

	public TextFieldBuilder right()
	{
		return this.setStyle("text-align", "right");
	}

	public TextFieldBuilder color(final String color)
	{
		return this.setStyle("color", color);
	}

	public TextFieldBuilder setStyle(final String name, final String value)
	{
		this.field.getStyle().set(name, value);

		return this;
	}

	public TextFieldBuilder placeholder(final String placeholder)
	{
		this.field.setPlaceholder(placeholder);

		return this;
	}

	public TextFieldBuilder requiredIndicatorVisible(final boolean visible)
	{
		this.field.setRequiredIndicatorVisible(visible);

		return this;
	}

	public TextFieldBuilder prefix(final VaadinIcon icon)
	{
		this.field.setPrefixComponent(icon.create());

		return this;
	}

	public TextFieldBuilder onBlur(final HasValue.ValueChangeListener<? super ComponentValueChangeEvent<TextField, String>> listener)
	{
		return this.changeListener(ValueChangeMode.EAGER, listener);
	}

	public TextFieldBuilder changeListener(final ValueChangeMode mode, final HasValue.ValueChangeListener<? super ComponentValueChangeEvent<TextField, String>> listener)
	{
		this.field.setValueChangeMode(mode);

		this.field.addValueChangeListener(listener);

		return this;
	}

	public TextFieldBuilder pattern(final String pattern)
	{
		this.field.setPattern(pattern);

		return this;
	}

	public TextFieldBuilder maxLenght(final int length)
	{
		this.field.setMaxLength(length);

		return this;
	}

	public TextFieldBuilder prefixIcon(final VaadinIcon icon)
	{
		this.field.setPrefixComponent(icon.create());

		return this;
	}

	public TextFieldBuilder asPhoneNumber()
	{
		this.field.setRequiredIndicatorVisible(true);
		this.field.setPattern("^[+]?[(]?[0-9]{3}[)]?[-s.]?[0-9]{3}[-s.]?[0-9]{4,6}$");
		this.field.setAllowedCharPattern("[0-9()+-]");
		this.field.setMinLength(5);
		this.field.setMaxLength(18);
		this.field.setAutoselect(true);
		this.field.setAutofocus(true);

		this.placeholder("(xxx) xxx-xxxx");

		this.prefixIcon(VaadinIcon.PHONE);

		return this;
	}

	public TextFieldBuilder asEmailAddress()
	{
		this.field.setRequiredIndicatorVisible(true);
		this.field.setPattern("^\\S+@\\S+\\.\\S+$");
		this.field.setAutoselect(true);
		this.field.setAutofocus(true);

		this.placeholder("you@example.com");

		this.prefixIcon(VaadinIcon.ENVELOPE);

		return this;
	}
}
