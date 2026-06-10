package com.pysarivka.WeekEnds.domain;

import org.springframework.security.core.GrantedAuthority;

import lombok.Getter;

@Getter
public enum UserRole implements GrantedAuthority{
	ROLE_ADMIN, ROLE_USER, ROLE_PLAYER;

	@Override
	public String getAuthority() {
		return name();
	}
}
