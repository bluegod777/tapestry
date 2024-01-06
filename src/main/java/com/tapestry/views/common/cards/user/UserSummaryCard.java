package com.tapestry.views.common.cards.user;

import com.tapestry.data.entity.User;
import com.tapestry.data.repositories.TapestryRepository;
import com.tapestry.security.AuthenticatedUser;
import com.tapestry.views.common.builders.HorizontalLayoutBuilder;
import com.tapestry.views.common.builders.HtmlLabelBuilder;
import com.tapestry.views.common.builders.ImageBuilder;
import com.tapestry.views.common.builders.VerticalLayoutBuilder;
import com.tapestry.views.common.views.TapestryCardSkeleton;
import com.vaadin.flow.component.Component;

public class UserSummaryCard extends TapestryCardSkeleton<User>
{
	public UserSummaryCard(final User source, AuthenticatedUser authenticatedUser, final TapestryRepository repository)
	{
		super(source, authenticatedUser, repository);
	}

	@Override
	public Component getContent()
	{
		this.addClassName("user-summary-card");
		this.setSpacing(false);

		// The user photo
		final var userPhoto = ImageBuilder.create().url(this.getSource().getProfilePictureUrl(), "photo").className("profile-image").build();

		// The user's name
		final var name = HtmlLabelBuilder.create().large().text(this.getSource().getFullName()).build();

		/* @formatter:off */

		return HorizontalLayoutBuilder.create().tight()
				.add( userPhoto )
				.add( VerticalLayoutBuilder.create().tight().add( name ).build() )
			.build();

		/* @formatter:on */
	}
}
