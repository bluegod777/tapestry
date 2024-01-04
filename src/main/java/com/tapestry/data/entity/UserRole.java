package com.tapestry.data.entity;

import com.tapestry.data.type.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserRole extends AbstractEntity
{
	private Role role;

	// -------------------------------------------------------------------
	//
	// -------------------------------------------------------------------
}
