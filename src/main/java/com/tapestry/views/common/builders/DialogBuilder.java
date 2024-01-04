package com.tapestry.views.common.builders;

import com.tapestry.views.common.components.TButton;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.icon.Icon;

public class DialogBuilder<T extends Component>
{
	private Dialog dialog;
	private T dialogContent;

	// -------------------------------------------------------------------
	//
	// -------------------------------------------------------------------
	public static <T extends Component> DialogBuilder<T> create()
	{
		return new DialogBuilder<T>();
	}

	public DialogBuilder()
	{
		this.dialog = new Dialog();
	}

	public Dialog build()
	{
		return this.dialog;
	}

	public DialogBuilder<T> closable()
	{
		Button closeButton = new Button(new Icon("lumo", "cross"), (e) -> this.dialog.close());

		// closeButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

		this.dialog.getHeader().add(closeButton);

		return this;
	}

	public DialogBuilder<T> setContent(final T content)
	{
		this.dialog.add(this.dialogContent = content);

		return this;
	}

	public DialogBuilder<T> close()
	{
		return this.button("Close", e -> this.dialog.close());
	}

	public DialogBuilder<T> button(final String text, final ComponentEventListener<ClickEvent<Button>> listener)
	{
		TButton button = ButtonBuilder.create().text(text).listener(listener).build();

		return this.addFooterButton(button);
	}

	public DialogBuilder<T> primaryButton(final String text, final ComponentEventListener<ClickEvent<Button>> listener)
	{
		TButton button = ButtonBuilder.create().primary().text(text).listener(listener).build();

		return this.addFooterButton(button);
	}

	public DialogBuilder<T> setHeaderTitle(final String title)
	{
		this.dialog.setHeaderTitle(title);

		return this;
	}

	public DialogBuilder<T> addFooterButton(final Button button)
	{
		this.dialog.getFooter().add(button);

		if (button instanceof TButton)
		{
			((TButton) button).setDialog(this.dialog);
			((TButton) button).setDialogContent(this.dialogContent);
		}

		return this;
	}

	public DialogBuilder<T> asError(final String message)
	{
		this.dialog.setHeaderTitle("OOOPPPS - Error Reported");

		/* @formatter:off */

		this.dialog.add(
				VerticalLayoutBuilder.create().addClass("highlite-sidebar-red").add
				(
						HtmlLabelBuilder.create().textf(message).build()
				).build()
			);

		/* @formatter:on */

		Button closeButton = ButtonBuilder.create().text("Close").primary().listener(e -> this.dialog.close()).build();

		this.dialog.getFooter().add(closeButton);

		return this;
	}

	public DialogBuilder<T> addClassName(final String className)
	{
		this.dialog.addClassName(className);

		return this;
	}

	// -------------------------------------------------------------------
	// Some fast builder
	// -------------------------------------------------------------------

	public DialogBuilder<T> asUnderConstruction()
	{
		this.dialog.add(new Image("https://webdav-001.ayt360.org/repository/public/compass/images/under-construction.png", ""));

		Button closeButton = ButtonBuilder.create().text("Close").primary().listener(e -> this.dialog.close()).build();

		this.dialog.getFooter().add(closeButton);

		return this;
	}

	public DialogBuilder<T> asRequiredFieldMissing(final String fieldName)
	{
		this.dialog.setHeaderTitle("Missing Field");

		/* @formatter:off */

		this.dialog.add(
				VerticalLayoutBuilder.create().addClass("highlite-sidebar-red").add
				(
						HtmlLabelBuilder.create().textf("Field is required and can not be left blank : <b>%s</b>", fieldName).build()
				).build()
			);

		/* @formatter:on */

		Button closeButton = ButtonBuilder.create().text("Close").primary().listener(e -> this.dialog.close()).build();

		this.dialog.getFooter().add(closeButton);

		return this;
	}

}
