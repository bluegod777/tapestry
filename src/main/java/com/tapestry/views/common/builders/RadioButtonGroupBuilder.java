package com.tapestry.views.common.builders;

import com.vaadin.flow.component.AbstractField.ComponentValueChangeEvent;
import com.vaadin.flow.component.HasValue.ValueChangeListener;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;

public class RadioButtonGroupBuilder<T>
{
	RadioButtonGroup<T> field;

	public static <T> RadioButtonGroupBuilder<T> create()
	{
		return new RadioButtonGroupBuilder<T>();
	}

	public RadioButtonGroupBuilder()
	{
		this.field = new RadioButtonGroup<>();
	}

	public RadioButtonGroup<T> build()
	{
		return this.field;
	}

	public RadioButtonGroupBuilder<T> label(final String label)
	{
		this.field.setLabel(label);

		return this;
	}

	public RadioButtonGroupBuilder<T> widthFull()
	{
		this.field.setWidthFull();

		return this;
	}

	public RadioButtonGroupBuilder<T> heightFull()
	{
		this.field.setHeightFull();

		return this;
	}

	public RadioButtonGroupBuilder<T> className(final String className)
	{
		this.field.setClassName(className);

		return this;
	}

	public RadioButtonGroupBuilder<T> size(final int fontSize)
	{
		return this.setStyle("font-size", String.format("%dpx", fontSize));
	}

	public RadioButtonGroupBuilder<T> required()
	{
		this.field.setRequired(true);
		this.field.setRequiredIndicatorVisible(true);

		return this;
	}

	public RadioButtonGroupBuilder<T> center()
	{
		return this.setStyle("text-align", "center");
	}

	public RadioButtonGroupBuilder<T> selfCenter()
	{
		return this.setStyle("align-self", "center");
	}

	public RadioButtonGroupBuilder<T> paddingSmall()
	{
		return this.setStyle("padding", "var(--lumo-space-s)");
	}

	public RadioButtonGroupBuilder<T> paddingxSmall()
	{
		return this.setStyle("padding", "var(--lumo-space-xs)");
	}

	public RadioButtonGroupBuilder<T> paddingxxSmall()
	{
		return this.setStyle("padding", "var(--lumo-space-xxs)");
	}

	public RadioButtonGroupBuilder<T> setStyle(final String name, final String value)
	{
		this.field.getStyle().set(name, value);

		return this;
	}

	@SuppressWarnings("unchecked")
	public RadioButtonGroupBuilder<T> items(final T... items)
	{
		this.field.setItems(items);

		return this;
	}

	public RadioButtonGroupBuilder<T> value(final T value)
	{
		this.field.setValue(value);

		return this;
	}

	public RadioButtonGroupBuilder<T> valueChange(final ValueChangeListener<? super ComponentValueChangeEvent<RadioButtonGroup<T>, T>> listener)
	{
		this.field.addValueChangeListener(listener);

		return this;
	}
}
