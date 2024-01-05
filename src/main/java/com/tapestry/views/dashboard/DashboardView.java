package com.tapestry.views.dashboard;

import com.tapestry.data.repositories.TapestryRepository;
import com.tapestry.security.AuthenticatedUser;
import com.tapestry.views.MainLayout;
import com.tapestry.views.common.builders.ButtonBuilder;
import com.tapestry.views.common.builders.HtmlLabelBuilder;
import com.tapestry.views.common.builders.VerticalLayoutBuilder;
import com.tapestry.views.common.views.TapestryRouterViewSkeleton;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.board.Board;
import com.vaadin.flow.component.board.Row;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

import jakarta.annotation.security.PermitAll;

@PageTitle("Dashboard")
@Route(value = "dashboard", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
@PermitAll
public class DashboardView extends TapestryRouterViewSkeleton<DashboardView> implements BeforeEnterObserver
{
	// -------------------------------------------------------------------
	//
	// -------------------------------------------------------------------
	public DashboardView(final AuthenticatedUser authenticatedUser, final TapestryRepository repository)
	{
		super(authenticatedUser, repository);

		this.init();
	}

	@Override
	public DashboardView init()
	{
		Board board = new Board();

		Row ownerRow = new Row();
		ownerRow.add(this.getOwnerCard(), 3);
		board.add(ownerRow);

		Row relationshipRow = new Row();
		relationshipRow.add(this.getRelationshipCard());
		relationshipRow.add(this.getRelationshipCard());
		relationshipRow.add(this.getRelationshipCard());
		board.add(relationshipRow);

		Row addRow = new Row();
		addRow.add(this.getAddMemberCard(), 3);
		board.add(addRow);

		this.add(board);

		return this;
	}

	@Override
	public void beforeEnter(BeforeEnterEvent event)
	{
		// TODO Auto-generated method stub

	}

	public Component getOwnerCard()
	{
		var outer = VerticalLayoutBuilder.create().tight().build();

		var card = VerticalLayoutBuilder.create().yellow().margin(true).build();
		card.add(HtmlLabelBuilder.create().text("Owner").build());

		outer.add(card);

		return outer;
	}

	public Component getRelationshipCard()
	{
		var outer = VerticalLayoutBuilder.create().tight().build();

		var card = VerticalLayoutBuilder.create().green().margin(true).build();
		card.add(HtmlLabelBuilder.create().text("Relationship").build());

		outer.add(card);

		return outer;
	}

	public Component getAddMemberCard()
	{
		var outer = VerticalLayoutBuilder.create().tight().build();

		var card = VerticalLayoutBuilder.create().margin(true).build();
		card.add(ButtonBuilder.create().text("Add Family/Friend").primary().alignCenter().build());

		outer.add(card);

		return outer;
	}

}
