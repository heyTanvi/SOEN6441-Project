package com.soen.app.covidtracker.domain.impl;

import com.soen.app.covidtracker.domain.Domain;

public class DomainEO implements Domain {
	private String id;

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

}
