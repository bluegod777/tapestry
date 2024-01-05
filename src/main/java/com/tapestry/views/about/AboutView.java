package com.tapestry.views.about;

import com.tapestry.views.MainLayout;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;

import jakarta.annotation.security.PermitAll;

@PageTitle("About")
@Route(value = "about", layout = MainLayout.class)
@PermitAll
public class AboutView extends VerticalLayout
{

	public AboutView()
	{
		this.setSpacing(false);

		Image img = new Image("images/empty-plant.png", "placeholder plant");
		img.setWidth("200px");
		this.add(img);

		H2 header = new H2("This place intentionally left empty");
		header.addClassNames(Margin.Top.XLARGE, Margin.Bottom.MEDIUM);
		this.add(header);
		this.add(new Paragraph("It’s a place where you can grow your own UI 🤗"));

		this.setSizeFull();
		this.setJustifyContentMode(JustifyContentMode.CENTER);
		this.setDefaultHorizontalComponentAlignment(Alignment.CENTER);
		this.getStyle().set("text-align", "center");
	}

}
