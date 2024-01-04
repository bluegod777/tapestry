package com.tapestry.views.common.components;

import java.util.Base64;

import com.tapestry.views.common.builders.ButtonBuilder;
import com.tapestry.views.common.builders.HtmlLabelBuilder;
import com.tapestry.views.common.builders.ImageBuilder;
import com.tapestry.views.common.builders.VerticalLayoutBuilder;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.upload.SucceededEvent;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TImageUploader extends VerticalLayout implements ComponentEventListener<SucceededEvent>
{
	private static final String CARD_UPLOAD = "CARD_UPLOAD";
	private static final String CARD_CURRENT = "CARD_CURRENT";

	private CardSwitcherLayout switcher;

	private HtmlLabel label;
	private Upload image;
	private MemoryBuffer uploadBuffer;

	private VerticalLayout currentImageController;
	private VerticalLayout currentImageWrapper;

	// -------------------------------------------------------------------
	//
	// -------------------------------------------------------------------
	public TImageUploader()
	{
		this.init();
	}

	private void init()
	{
		this.setClassName("image-uploader");
		this.setSpacing(false);

		// Allocate the memory buffer
		this.uploadBuffer = new MemoryBuffer();

		// Allocate the uploader
		this.image = new Upload(this.uploadBuffer);
		this.image.setWidthFull();

		// Listen for success
		this.image.addSucceededListener(this);

		// This is the current image that the user selected
		this.currentImageController = VerticalLayoutBuilder.create().tight().build();
		this.currentImageController.add(this.currentImageWrapper = VerticalLayoutBuilder.create().tight().sizeUndefined().addClass("current-image").build());
		this.currentImageController.add(ButtonBuilder.create().text("Clear Image").alignCenter().listener(e ->
		{
			// Clear everything out
			this.image.clearFileList();

			// Switch over to the current tab
			this.switcher.showCard(TImageUploader.CARD_UPLOAD);

		}).build());

		// Put it together
		this.switcher = new CardSwitcherLayout()
		{
			@Override
			protected void init()
			{
				this.addClassName("switcher");
			}
		};
		this.switcher.addCard(TImageUploader.CARD_UPLOAD, this.image);
		this.switcher.addCard(TImageUploader.CARD_CURRENT, this.currentImageController);

		this.switcher.showCard(TImageUploader.CARD_UPLOAD);

		this.add(this.label = HtmlLabelBuilder.create().className("tapestry-label").build());
		this.add(this.switcher);
	}

	public TImageUploader setLabel(final String label)
	{
		this.label.setText(label);

		return this;
	}

	public TImageUploader setDropLabel(final String dropLabel)
	{
		this.image.setDropLabel(HtmlLabelBuilder.create().text(dropLabel).build());

		return this;
	}

	@Override
	public void onComponentEvent(SucceededEvent event)
	{
		try
		{
			TImageUploader.log.info("TImageUploader::something was uploaded : {}", event.getContentLength());

			TImageUploader.log.info("   > filename  : {}", event.getFileName());
			TImageUploader.log.info("   > MIME TYPE : {}", event.getMIMEType());
			TImageUploader.log.info("   > Length    : {}", event.getContentLength());

			String encoded = TImageUploader.encodeImageToBase64(event.getMIMEType(), this.uploadBuffer.getInputStream().readAllBytes());

			this.currentImageWrapper.removeAll();
			this.currentImageWrapper.add(ImageBuilder.create().url(encoded, "").build());

			// Switch over to the current tab
			this.switcher.showCard(TImageUploader.CARD_CURRENT);
		}

		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public static String encodeImageToBase64(final String mimeType, byte[] imageBytes)
	{
		switch (mimeType)
		{
		case "image/jpeg":
			return "data:image/jpeg;base64," + Base64.getEncoder().encodeToString(imageBytes);
		case "image/png":
			return "data:image/png;base64," + Base64.getEncoder().encodeToString(imageBytes);
		case "image/gif":
			return "data:image/gif;base64," + Base64.getEncoder().encodeToString(imageBytes);
		default:
			return "https://cdn.dribbble.com/users/2639810/screenshots/9403030/41_4x.jpg";
		}

	}
}
