package com.tapestry.views.common.builders;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.AnchorTarget;

public class AnchorBuilder
{
	private final Anchor button;

	public static AnchorBuilder create()
	{
		return new AnchorBuilder();
	}

	public AnchorBuilder()
	{
		this.button = new Anchor();
	}

	public Anchor build()
	{
		return this.button;
	}

	public AnchorBuilder href(final String href)
	{
		this.button.setHref(href);
		this.button.setTarget(AnchorTarget.BLANK);

		return this;
	}

	public AnchorBuilder text(final String text)
	{
		this.button.setText(text);

		return this;
	}

	public AnchorBuilder stopPropogation(final ComponentEventListener<ClickEvent<Button>> listener)
	{
		this.button.getElement().addEventListener("click", e ->
		{
			listener.onComponentEvent(new ClickEvent<>(this.button));
		}).addEventData("event.stopPropagation()");

		return this;
	}

	public AnchorBuilder className(final String className)
	{
		this.button.addClassName(className);

		return this;
	}

	public AnchorBuilder toolTip(final String toolTip)
	{
		// WebComponentUtils.addToolTip(this.button, toolTip);

		return this;
	}

	public AnchorBuilder widthFull()
	{
		this.button.setWidthFull();

		return this;
	}

	public AnchorBuilder heightFull()
	{
		this.button.setHeightFull();

		return this;
	}

	public AnchorBuilder heightAuto()
	{
		this.button.getStyle().set("height", "auto");

		return this;
	}

	public AnchorBuilder setStyle(final String name, final String value)
	{
		this.button.getElement().getStyle().set(name, value);

		return this;
	}

	public AnchorBuilder width(final int inPixels)
	{
		return this.setStyle("width", String.format("%dpx", inPixels));
	}

	public AnchorBuilder minWidthAuto()
	{
		return this.setStyle("min-width", "auto");
	}

	public AnchorBuilder alignCenter()
	{
		return this.setStyle("align-self", "center");
	}

	public AnchorBuilder alignRight()
	{
		return this.setStyle("align-self", "flex-end");
	}

	public AnchorBuilder alignBottom()
	{
		return this.setStyle("align-self", "flex-end");
	}

	public AnchorBuilder enable(final boolean enabled)
	{
		this.button.setEnabled(enabled);

		return this;
	}

	public AnchorBuilder property(final String name, final String value)
	{
		this.button.getElement().setProperty(name, value);

		return this;
	}

}
