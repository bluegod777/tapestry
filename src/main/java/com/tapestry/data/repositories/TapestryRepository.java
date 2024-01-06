package com.tapestry.data.repositories;

import org.springframework.stereotype.Repository;

import com.tapestry.data.entity.User;
import com.tapestry.utils.CalendarUtils;

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
	public static User getParent()
	{
		User parent = User.builder().firstName("Sally").lastName("Parent").email("omey@tapestrycard.com").mobilePhoneNumber("3605613793").build();

		parent.getChildren().add(TapestryRepository.getChildPreTeem());
		parent.getChildren().add(TapestryRepository.getChildTeen());
		parent.getChildren().add(TapestryRepository.getChildYoungAdult());

		return parent;
	}

	public static User getChildPreTeem()
	{
		User parent = User.builder().firstName("Johnnie").lastName("Child").birthdate(CalendarUtils.getInstance(2015, 1, 3)).email("omey@tapestrycard.com").mobilePhoneNumber("3605613793").build();

		return parent;
	}

	public static User getChildTeen()
	{
		User parent = User.builder().firstName("Dee").lastName("Child").birthdate(CalendarUtils.getInstance(2007, 5, 7)).email("omey@tapestrycard.com").mobilePhoneNumber("3605613793").build();

		return parent;
	}

	public static User getChildYoungAdult()
	{
		User parent = User.builder().firstName("Ryan").lastName("Child").birthdate(CalendarUtils.getInstance(1999, 8, 16)).email("omey@tapestrycard.com").mobilePhoneNumber("3605613793").build();

		return parent;
	}

}
