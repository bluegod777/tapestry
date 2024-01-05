package com.tapestry.views.about;

import com.tapestry.data.repositories.TapestryRepository;
import com.tapestry.security.AuthenticatedUser;
import com.tapestry.views.MainLayout;
import com.tapestry.views.common.builders.HtmlLabelBuilder;
import com.tapestry.views.common.builders.ImageBuilder;
import com.tapestry.views.common.builders.TapestryBuilder;
import com.tapestry.views.common.views.TapestryRouterViewSkeleton;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import jakarta.annotation.security.PermitAll;

@PageTitle("About")
@Route(value = "about", layout = MainLayout.class)
@PermitAll
public class AboutView extends TapestryRouterViewSkeleton<AboutView> implements BeforeEnterObserver
{

	public AboutView(final AuthenticatedUser authenticatedUser, final TapestryRepository repository)
	{
		super(authenticatedUser, repository);

		this.init();
	}

	@Override
	public AboutView init()
	{
		this.setSizeFull();
		this.setSpacing(false);
		this.setClassName("about-view");

		this.add(TapestryBuilder.getTapestryLogo());
		this.add(HtmlLabelBuilder.create().className("consumer-tag-line").text("Literacy through action").build());

		var img = ImageBuilder.create().url("https://webdav-001.ayt360.org/repository/public/tapestry.com/avatars/fun-3d-cartoon-white-labrador-retriever_183364-118249.avif", "").className("img-avatar").build();
		this.add(img);

		// this.setJustifyContentMode(JustifyContentMode.CENTER);
		this.setDefaultHorizontalComponentAlignment(Alignment.CENTER);
		this.getStyle().set("text-align", "center");

		return this;
	}

	@Override
	public void beforeEnter(BeforeEnterEvent event)
	{
		// TODO Auto-generated method stub

	}

}
