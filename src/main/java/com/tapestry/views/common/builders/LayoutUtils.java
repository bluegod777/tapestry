package com.tapestry.views.common.builders;

import com.tapestry.utils.ValidateUtils;
import com.tapestry.views.common.components.HtmlLabel;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class LayoutUtils
{
	// -------------------------------------------------------------------
	// Some vertical labeled controls
	// -------------------------------------------------------------------
	public static VerticalLayout getVerticalLabledValue(final HtmlLabel label)
	{
		return LayoutUtils.getVerticalLabledValue(label, null);

	}

	public static VerticalLayout getVerticalLabledValue(final HtmlLabel label, final String labelClassName)
	{
		return VerticalLayoutBuilder.create().tight().add(HtmlLabelBuilder.create().className(labelClassName).text(label.getTitle()).build(), label).build();

	}

	public static VerticalLayout getVerticalLabledValue(final String label, final String value)
	{
		return LayoutUtils.getVerticalIconLabelValue(null, label, null, value, null);
	}

	public static VerticalLayout getVerticalLabledValue(final String label, final String labelClassName, final String value, final String valueClassName)
	{
		return LayoutUtils.getVerticalIconLabelValue(null, label, labelClassName, value, valueClassName);
	}

	public static VerticalLayout getVerticalIconLabelValue(final VaadinIcon icon, final String label, final String labelClassName, final String value, final String valueClassName)
	{
		final VerticalLayout h = VerticalLayoutBuilder.create().tight().spacing(true).build();

		if (icon != null)
		{
			h.add(icon.create());
		}

		if (ValidateUtils.isNotNullOrEmpty(label))
		{
			// Create the label & value
			final HtmlLabel l = HtmlLabelBuilder.create().small().text(label).className(labelClassName).build();

			h.add(l);
		}

		if (ValidateUtils.isNotNullOrEmpty(value))
		{
			// Create the label & value
			final HtmlLabel v = HtmlLabelBuilder.create().small().text(value).className(valueClassName).build();

			h.add(v);
		}

		return h;
	}

	public static VerticalLayout getVerticalLabledValue(final Div div, final String labelClassName)
	{
		return VerticalLayoutBuilder.create().tight().add(HtmlLabelBuilder.create().className(labelClassName).text(div.getTitle().orElse("")).build(), div).build();

	}

}
