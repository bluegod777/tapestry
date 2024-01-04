/**
 *
 */
package com.tapestry.views.common.chips;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

public class ChipField extends HorizontalLayout
{

	public static final String CLASS_NAME = "chip-field";

	public ChipField()
	{
		this.init();
	}

	private void init()
	{
		this.addClassName(ChipField.CLASS_NAME);
	}

	public ChipField addChip(final Chip... chips)
	{
		for (final Chip chip : chips)
		{
			this.add(chip);
		}

		return this;
	}

	public ChipField center()
	{
		this.getStyle().set("align-self", "center");

		return this;
	}

	public void clearChips()
	{
		this.removeAll();
	}

}
