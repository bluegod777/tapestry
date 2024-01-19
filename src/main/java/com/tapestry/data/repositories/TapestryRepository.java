package com.tapestry.data.repositories;

import org.springframework.stereotype.Repository;

import com.tapestry.data.entity.UserOld;
import com.tapestry.utils.CalendarUtils;
import com.tapestry.views.common.builders.ImageBuilder;

import lombok.Getter;

@Repository
@Getter
public class TapestryRepository
{

	// -------------------------------------------------------------------
	// This holds all the repositories that we care about
	//
	// We had some helper methods to make access easier
	// -------------------------------------------------------------------
	public TapestryRepository()
	{

	}

	// -------------------------------------------------------------------
	// Some simulation factories
	//
	// We need something that can generate stuff for us
	// -------------------------------------------------------------------
	public static UserOld getParent()
	{
		UserOld parent = UserOld.builder().firstName("Sally").lastName("Parent").email("omey@tapestrycard.com").mobilePhoneNumber("3605613793").profilePictureUrl(ImageBuilder.ROOT + "avatars/super-mom.jpg").build();

		parent.getChildren().add(TapestryRepository.getChildPreTeem());
		parent.getChildren().add(TapestryRepository.getChildTeen());
		parent.getChildren().add(TapestryRepository.getChildYoungAdult());

		return parent;
	}

	public static UserOld getChildPreTeem()
	{
		UserOld parent = UserOld.builder().firstName("Johnnie").lastName("Child").birthdate(CalendarUtils.getInstance(2015, 1, 3)).email("omey@tapestrycard.com").mobilePhoneNumber("3605613793").profilePictureUrl(ImageBuilder.ROOT + "avatars/son-preteen.jpg").build();

		return parent;
	}

	public static UserOld getChildTeen()
	{
		UserOld parent = UserOld.builder().firstName("Dee").lastName("Child").birthdate(CalendarUtils.getInstance(2007, 5, 7)).email("omey@tapestrycard.com").mobilePhoneNumber("3605613793").profilePictureUrl(ImageBuilder.ROOT + "avatars/daughter-teen.jpg").build();

		return parent;
	}

	public static UserOld getChildYoungAdult()
	{
		UserOld parent = UserOld.builder().firstName("Ryan").lastName("Child").birthdate(CalendarUtils.getInstance(1999, 8, 16)).email("omey@tapestrycard.com").mobilePhoneNumber("3605613793").profilePictureUrl(ImageBuilder.ROOT + "avatars/son-young-adult.jpg").build();

		return parent;
	}

}
