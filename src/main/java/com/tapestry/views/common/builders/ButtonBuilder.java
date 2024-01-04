package com.tapestry.views.common.builders;

import com.tapestry.views.common.components.TButton;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.icon.VaadinIcon;

public class ButtonBuilder
{
	private final TButton button;

	public static ButtonBuilder create()
	{
		return new ButtonBuilder();
	}

	public ButtonBuilder()
	{
		this.button = new TButton();
	}

	public TButton build()
	{
		return this.button;
	}

	public ButtonBuilder text(final String text)
	{
		this.button.setText(text);

		return this;
	}

	public ButtonBuilder icon(final VaadinIcon icon)
	{
		this.button.setIcon(icon.create());

		return this;
	}

	public ButtonBuilder listener(final ComponentEventListener<ClickEvent<Button>> listener)
	{
		this.button.addClickListener(listener);

		return this;
	}

	public ButtonBuilder stopPropogation(final ComponentEventListener<ClickEvent<Button>> listener)
	{
		this.button.getElement().addEventListener("click", e ->
		{
			listener.onComponentEvent(new ClickEvent<>(this.button));
		}).addEventData("event.stopPropagation()");

		return this;
	}

	public ButtonBuilder className(final String className)
	{
		this.button.addClassName(className);

		return this;
	}

	public ButtonBuilder variants(final ButtonVariant... variants)
	{
		if ((variants != null) && (variants.length > 0))
		{
			this.button.addThemeVariants(variants);
		}

		return this;
	}

	public ButtonBuilder toolTip(final String toolTip)
	{
		// WebComponentUtils.addToolTip(this.button, toolTip);

		return this;
	}

	public ButtonBuilder widthFull()
	{
		this.button.setWidthFull();

		return this;
	}

	public ButtonBuilder heightFull()
	{
		this.button.setHeightFull();

		return this;
	}

	public ButtonBuilder heightAuto()
	{
		this.button.getStyle().set("height", "auto");

		return this;
	}

	public ButtonBuilder setStyle(final String name, final String value)
	{
		this.button.getElement().getStyle().set(name, value);

		return this;
	}

	public ButtonBuilder width(final int inPixels)
	{
		return this.setStyle("width", String.format("%dpx", inPixels));
	}

	public ButtonBuilder minWidthAuto()
	{
		return this.setStyle("min-width", "auto");
	}

	public ButtonBuilder alignCenter()
	{
		return this.setStyle("align-self", "center");
	}

	public ButtonBuilder alignRight()
	{
		return this.setStyle("align-self", "flex-end");
	}

	public ButtonBuilder alignBottom()
	{
		return this.setStyle("align-self", "flex-end");
	}

	public ButtonBuilder primary()
	{
		this.variants(ButtonVariant.LUMO_PRIMARY);

		return this;
	}

	public ButtonBuilder contrast()
	{
		this.variants(ButtonVariant.LUMO_CONTRAST);

		return this;
	}

	public ButtonBuilder error()
	{
		this.variants(ButtonVariant.LUMO_ERROR);

		return this;
	}

	public ButtonBuilder icon()
	{
		this.variants(ButtonVariant.LUMO_ICON);

		return this;
	}

	public ButtonBuilder large()
	{
		this.variants(ButtonVariant.LUMO_LARGE);

		return this;
	}

	public ButtonBuilder small()
	{
		this.variants(ButtonVariant.LUMO_SMALL);

		return this;
	}

	public ButtonBuilder success()
	{
		this.variants(ButtonVariant.LUMO_SUCCESS);

		return this;
	}

	public ButtonBuilder tertiary()
	{
		this.variants(ButtonVariant.LUMO_TERTIARY);

		return this;
	}

	public ButtonBuilder tertiaryInLine()
	{
		this.variants(ButtonVariant.LUMO_TERTIARY_INLINE);

		return this;
	}

	public ButtonBuilder enable(final boolean enabled)
	{
		this.button.setEnabled(enabled);

		return this;
	}

	public ButtonBuilder property(final String name, final String value)
	{
		this.button.getElement().setProperty(name, value);

		return this;
	}

}
