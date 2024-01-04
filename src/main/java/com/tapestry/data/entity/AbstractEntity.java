package com.tapestry.data.entity;

import java.time.OffsetDateTime;

import com.vaadin.flow.component.template.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public abstract class AbstractEntity
{

	@Id
	private Long id;

	private int version;

	private OffsetDateTime createdOn;

	private OffsetDateTime updatedOn;

	private String createdBy;

	private String updatedBy;

	// -------------------------------------------------------------------
	//
	// -------------------------------------------------------------------

	@Override
	public int hashCode()
	{
		if (this.getId() != null)
		{
			return this.getId().hashCode();
		}
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj)
	{
		if (!(obj instanceof AbstractEntity that))
		{
			return false; // null or not an AbstractEntity class
		}
		if (this.getId() != null)
		{
			return this.getId().equals(that.getId());
		}
		return super.equals(that);
	}

}
