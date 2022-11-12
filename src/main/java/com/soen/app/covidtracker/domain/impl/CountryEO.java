package com.soen.app.covidtracker.domain.impl;

import com.soen.app.covidtracker.domain.Country;

public class CountryEO extends GlobalEO implements Country {

	private String name;
	private String countryCode;

	public CountryEO() {

	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;

	}

	@Override
	public String getCountryCode() {
		return countryCode;
	}

	@Override
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

}
