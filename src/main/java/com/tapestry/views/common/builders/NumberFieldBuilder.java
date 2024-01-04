package com.tapestry.views.common.builders;

import com.vaadin.flow.component.AbstractField.ComponentValueChangeEvent;
import com.vaadin.flow.component.HasValue;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.data.value.ValueChangeMode;

public class NumberFieldBuilder
{

	private final NumberField field;

	public static NumberFieldBuilder create()
	{
		return new NumberFieldBuilder();
	}

	public NumberFieldBuilder()
	{
		this.field = new NumberField();
	}

	public NumberField build()
	{
		return this.field;
	}

	public NumberFieldBuilder label(final String label)
	{
		this.field.setLabel(label);

		return this;
	}

	public NumberFieldBuilder value(final Double value)
	{
		this.field.setValue(value);

		return this;
	}

	public NumberFieldBuilder widthFull()
	{
		this.field.setWidthFull();

		return this;
	}

	public NumberFieldBuilder heightFull()
	{
		this.field.setHeightFull();

		return this;
	}

	public NumberFieldBuilder className(final String className)
	{
		this.field.setClassName(className);

		return this;
	}

	public NumberFieldBuilder size(final int fontSize)
	{
		return this.setStyle("font-size", String.format("%dpx", fontSize));
	}

	public NumberFieldBuilder required()
	{
		this.field.setRequired(true);
		this.field.setRequiredIndicatorVisible(true);

		return this;
	}

	public NumberFieldBuilder center()
	{
		return this.setStyle("text-align", "center");
	}

	public NumberFieldBuilder selfCenter()
	{
		return this.setStyle("align-self", "center");
	}

	public NumberFieldBuilder paddingSmall()
	{
		return this.setStyle("padding", "var(--lumo-space-s)");
	}

	public NumberFieldBuilder paddingxSmall()
	{
		return this.setStyle("padding", "var(--lumo-space-xs)");
	}

	public NumberFieldBuilder paddingxxSmall()
	{
		return this.setStyle("padding", "var(--lumo-space-xxs)");
	}

	public NumberFieldBuilder right()
	{
		return this.setStyle("text-align", "right");
	}

	public NumberFieldBuilder color(final String color)
	{
		return this.setStyle("color", color);
	}

	public NumberFieldBuilder setStyle(final String name, final String value)
	{
		this.field.getStyle().set(name, value);

		return this;
	}

	public NumberFieldBuilder placeholder(final String placeholder)
	{
		this.field.setPlaceholder(placeholder);

		return this;
	}

	public NumberFieldBuilder requiredIndicatorVisible(final boolean visible)
	{
		this.field.setRequiredIndicatorVisible(visible);

		return this;
	}

	public NumberFieldBuilder prefix(final VaadinIcon icon)
	{
		this.field.setPrefixComponent(icon.create());

		return this;
	}

	public NumberFieldBuilder onBlur(final HasValue.ValueChangeListener<? super ComponentValueChangeEvent<NumberField, Double>> listener)
	{
		return this.changeListener(ValueChangeMode.EAGER, listener);
	}

	public NumberFieldBuilder changeListener(final ValueChangeMode mode, final HasValue.ValueChangeListener<? super ComponentValueChangeEvent<NumberField, Double>> listener)
	{
		this.field.setValueChangeMode(mode);

		this.field.addValueChangeListener(listener);

		return this;
	}

	public NumberFieldBuilder prefixIcon(final VaadinIcon icon)
	{
		this.field.setPrefixComponent(icon.create());

		return this;
	}

}
