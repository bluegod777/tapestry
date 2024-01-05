package com.tapestry.data.dataservice;

import java.util.Arrays;
import java.util.List;

public class State
{
	/* @formatter:off */

	public final static List<State> STATES = Arrays.asList(
		    new State("Alabama", "AL"),
		    new State("Alaska", "AK"),
		    new State("Arizona", "AZ"),
		    new State("Arkansas", "AR"),
		    new State("California", "CA"),
		    new State("Colorado", "CO"),
		    new State("Connecticut", "CT"),
		    new State("Delaware", "DE"),
		    new State("Florida", "FL"),
		    new State("Georgia", "GA"),
		    new State("Hawaii", "HI"),
		    new State("Idaho", "ID"),
		    new State("Illinois", "IL"),
		    new State("Indiana", "IN"),
		    new State("Iowa", "IA"),
		    new State("Kansas", "KS"),
		    new State("Kentucky", "KY"),
		    new State("Louisiana", "LA"),
		    new State("Maine", "ME"),
		    new State("Maryland", "MD"),
		    new State("Massachusetts", "MA"),
		    new State("Michigan", "MI"),
		    new State("Minnesota", "MN"),
		    new State("Mississippi", "MS"),
		    new State("Missouri", "MO"),
		    new State("Montana", "MT"),
		    new State("Nebraska", "NE"),
		    new State("Nevada", "NV"),
		    new State("New Hampshire", "NH"),
		    new State("New Jersey", "NJ"),
		    new State("New Mexico", "NM"),
		    new State("New York", "NY"),
		    new State("North Carolina", "NC"),
		    new State("North Dakota", "ND"),
		    new State("Ohio", "OH"),
		    new State("Oklahoma", "OK"),
		    new State("Oregon", "OR"),
		    new State("Pennsylvania", "PA"),
		    new State("Rhode Island", "RI"),
		    new State("South Carolina", "SC"),
		    new State("South Dakota", "SD"),
		    new State("Tennessee", "TN"),
		    new State("Texas", "TX"),
		    new State("Utah", "UT"),
		    new State("Vermont", "VT"),
		    new State("Virginia", "VA"),
		    new State("Washington", "WA"),
		    new State("West Virginia", "WV"),
		    new State("Wisconsin", "WI"),
		    new State("Wyoming", "WY")
			);

	/* @formatter:on */

	// -------------------------------------------------------------------
	//
	// -------------------------------------------------------------------
	private String name;
	private String abbreviation;

	// -------------------------------------------------------------------
	//
	// -------------------------------------------------------------------
	public State(String name, String abbreviation)
	{
		this.name = name;
		this.abbreviation = abbreviation;
	}

	public String getName()
	{
		return this.name;
	}

	public String getAbbreviation()
	{
		return this.abbreviation;
	}

	@Override
	public String toString()
	{
		return this.abbreviation + " (" + this.name + ")";
	}
}