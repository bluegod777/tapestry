package com.tapestry.views.common.builders;

import com.vaadin.flow.component.AbstractField.ComponentValueChangeEvent;
import com.vaadin.flow.component.HasValue;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.data.value.ValueChangeMode;

public class PasswordFieldBuilder
{

	private final PasswordField field;

	public static PasswordFieldBuilder create()
	{
		return new PasswordFieldBuilder();
	}

	public PasswordFieldBuilder()
	{
		this.field = new PasswordField();
	}

	public PasswordField build()
	{
		return this.field;
	}

	public PasswordFieldBuilder label(final String label)
	{
		this.field.setLabel(label);

		return this;
	}

	public PasswordFieldBuilder value(final String value)
	{
		this.field.setValue(value);

		return this;
	}

	public PasswordFieldBuilder widthFull()
	{
		this.field.setWidthFull();

		return this;
	}

	public PasswordFieldBuilder heightFull()
	{
		this.field.setHeightFull();

		return this;
	}

	public PasswordFieldBuilder className(final String className)
	{
		this.field.setClassName(className);

		return this;
	}

	public PasswordFieldBuilder size(final int fontSize)
	{
		return this.setStyle("font-size", String.format("%dpx", fontSize));
	}

	public PasswordFieldBuilder required()
	{
		this.field.setRequired(true);
		this.field.setRequiredIndicatorVisible(true);

		return this;
	}

	public PasswordFieldBuilder center()
	{
		return this.setStyle("text-align", "center");
	}

	public PasswordFieldBuilder selfCenter()
	{
		return this.setStyle("align-self", "center");
	}

	public PasswordFieldBuilder paddingSmall()
	{
		return this.setStyle("padding", "var(--lumo-space-s)");
	}

	public PasswordFieldBuilder right()
	{
		return this.setStyle("text-align", "right");
	}

	public PasswordFieldBuilder color(final String color)
	{
		return this.setStyle("color", color);
	}

	public PasswordFieldBuilder setStyle(final String name, final String value)
	{
		this.field.getStyle().set(name, value);

		return this;
	}

	public PasswordFieldBuilder placeholder(final String placeholder)
	{
		this.field.setPlaceholder(placeholder);

		return this;
	}

	public PasswordFieldBuilder requiredIndicatorVisible(final boolean visible)
	{
		this.field.setRequiredIndicatorVisible(visible);

		return this;
	}

	public PasswordFieldBuilder prefix(final VaadinIcon icon)
	{
		this.field.setPrefixComponent(icon.create());

		return this;
	}

	public PasswordFieldBuilder onBlur(final HasValue.ValueChangeListener<? super ComponentValueChangeEvent<PasswordField, String>> listener)
	{
		return this.changeListener(ValueChangeMode.EAGER, listener);
	}

	public PasswordFieldBuilder changeListener(final ValueChangeMode mode, final HasValue.ValueChangeListener<? super ComponentValueChangeEvent<PasswordField, String>> listener)
	{
		this.field.setValueChangeMode(mode);

		this.field.addValueChangeListener(listener);

		return this;
	}

	public PasswordFieldBuilder pattern(final String pattern)
	{
		this.field.setPattern(pattern);

		return this;
	}

	public PasswordFieldBuilder maxLenght(final int length)
	{
		this.field.setMaxLength(length);

		return this;
	}

	public PasswordFieldBuilder prefixIcon(final VaadinIcon icon)
	{
		this.field.setPrefixComponent(icon.create());

		return this;
	}

	public PasswordFieldBuilder asSocialSecurityNumber()
	{
		this.field.setPlaceholder("xxx-xx-xxxx");
		this.field.setPattern("\\d{3}-\\d{2}-\\d{4}");
		this.field.setValueChangeMode(ValueChangeMode.ON_CHANGE);

		this.prefixIcon(VaadinIcon.LOCK);

		return this;
	}

}
