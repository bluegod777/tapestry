package com.tapestry.views.common.cards.user;

import com.tapestry.data.entity.User;
import com.tapestry.data.repositories.TapestryRepository;
import com.tapestry.security.AuthenticatedUser;
import com.tapestry.utils.CalendarUtils;
import com.tapestry.views.common.builders.HorizontalLayoutBuilder;
import com.tapestry.views.common.builders.HtmlLabelBuilder;
import com.tapestry.views.common.builders.VerticalLayoutBuilder;
import com.tapestry.views.common.views.TapestryCardSkeleton;
import com.vaadin.flow.component.Component;

public class UserDetailCard extends TapestryCardSkeleton<User>
{
	public UserDetailCard(final User source, AuthenticatedUser authenticatedUser, final TapestryRepository repository)
	{
		super(source, authenticatedUser, repository);
	}

	@Override
	public Component getContent()
	{
		this.addClassName("user-summary-card");
		this.setSpacing(false);

		// The user's name
		final var name = HtmlLabelBuilder.create().large().text(this.getSource().getFullName()).build();

		final var birthdate = HtmlLabelBuilder.create().text(this.getSource().getBirthdate() != null ? CalendarUtils.format(this.getSource().getBirthdate()) : "").build();

		final var phone = HtmlLabelBuilder.create().text(this.getSource().getMobilePhoneNumber()).build();

		final var email = HtmlLabelBuilder.create().text(this.getSource().getEmail()).build();

		/* @formatter:off */

		return HorizontalLayoutBuilder.create().tight()
				.add( VerticalLayoutBuilder.create().tight().add( name, birthdate, phone, email ).build() )
			.build();

		/* @formatter:on */
	}

}
