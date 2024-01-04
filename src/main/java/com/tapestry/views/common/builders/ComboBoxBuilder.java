package com.tapestry.views.common.builders;

import java.util.Collection;

import com.vaadin.flow.component.ItemLabelGenerator;
import com.vaadin.flow.component.combobox.ComboBox;

public class ComboBoxBuilder<T>
{
	private ComboBox<T> field;

	public static <T> ComboBoxBuilder<T> create()
	{
		return new ComboBoxBuilder<>();
	}

	public ComboBoxBuilder()
	{
		this.field = new ComboBox<>();
	}

	public ComboBoxBuilder<T> items(Collection<T> items)
	{
		this.field.setItems(items);
		return this;
	}

	public ComboBoxBuilder<T> label(String label)
	{
		this.field.setLabel(label);
		return this;
	}

	// Add more methods to customize the ComboBox as needed

	public ComboBoxBuilder<T> itemLabelGenerator(final ItemLabelGenerator<T> itemLabelGenerator)
	{
		this.field.setItemLabelGenerator(itemLabelGenerator);

		return this;
	}

	public ComboBox<T> build()
	{
		return this.field;
	}
}
