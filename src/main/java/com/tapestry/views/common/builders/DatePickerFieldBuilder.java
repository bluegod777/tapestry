package com.tapestry.views.common.builders;

import java.time.LocalDate;

import com.vaadin.flow.component.AbstractField.ComponentValueChangeEvent;
import com.vaadin.flow.component.HasValue;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.icon.VaadinIcon;

public class DatePickerFieldBuilder
{

	private final DatePicker field;

	public static DatePickerFieldBuilder create()
	{
		return new DatePickerFieldBuilder();
	}

	public DatePickerFieldBuilder()
	{
		this.field = new DatePicker();
	}

	public DatePicker build()
	{
		return this.field;
	}

	public DatePickerFieldBuilder label(final String label)
	{
		this.field.setLabel(label);

		return this;
	}

	public DatePickerFieldBuilder value(final LocalDate value)
	{
		this.field.setValue(value);

		return this;
	}

	public DatePickerFieldBuilder widthFull()
	{
		this.field.setWidthFull();

		return this;
	}

	public DatePickerFieldBuilder heightFull()
	{
		this.field.setHeightFull();

		return this;
	}

	public DatePickerFieldBuilder className(final String className)
	{
		this.field.setClassName(className);

		return this;
	}

	public DatePickerFieldBuilder size(final int fontSize)
	{
		return this.setStyle("font-size", String.format("%dpx", fontSize));
	}

	public DatePickerFieldBuilder required()
	{
		this.field.setRequired(true);
		this.field.setRequiredIndicatorVisible(true);

		return this;
	}

	public DatePickerFieldBuilder center()
	{
		return this.setStyle("text-align", "center");
	}

	public DatePickerFieldBuilder selfCenter()
	{
		return this.setStyle("align-self", "center");
	}

	public DatePickerFieldBuilder paddingSmall()
	{
		return this.setStyle("padding", "var(--lumo-space-s)");
	}

	public DatePickerFieldBuilder paddingxSmall()
	{
		return this.setStyle("padding", "var(--lumo-space-xs)");
	}

	public DatePickerFieldBuilder paddingxxSmall()
	{
		return this.setStyle("padding", "var(--lumo-space-xxs)");
	}

	public DatePickerFieldBuilder right()
	{
		return this.setStyle("text-align", "right");
	}

	public DatePickerFieldBuilder color(final String color)
	{
		return this.setStyle("color", color);
	}

	public DatePickerFieldBuilder setStyle(final String name, final String value)
	{
		this.field.getStyle().set(name, value);

		return this;
	}

	public DatePickerFieldBuilder placeholder(final String placeholder)
	{
		this.field.setPlaceholder(placeholder);

		return this;
	}

	public DatePickerFieldBuilder requiredIndicatorVisible(final boolean visible)
	{
		this.field.setRequiredIndicatorVisible(visible);

		return this;
	}

	public DatePickerFieldBuilder prefix(final VaadinIcon icon)
	{
		this.field.setPrefixComponent(icon.create());

		return this;
	}

	public DatePickerFieldBuilder onBlur(final HasValue.ValueChangeListener<? super ComponentValueChangeEvent<DatePicker, LocalDate>> listener)
	{
		return this.changeListener(listener);
	}

	public DatePickerFieldBuilder changeListener(final HasValue.ValueChangeListener<? super ComponentValueChangeEvent<DatePicker, LocalDate>> listener)
	{
		this.field.addValueChangeListener(listener);

		return this;
	}

	public DatePickerFieldBuilder prefixIcon(final VaadinIcon icon)
	{
		this.field.setPrefixComponent(icon.create());

		return this;
	}

}
