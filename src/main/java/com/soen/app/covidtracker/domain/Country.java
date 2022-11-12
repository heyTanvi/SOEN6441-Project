package com.soen.app.covidtracker.domain;

public interface Country extends Global {

	String getName();
	
	void setName(String name);
	
	String getCountryCode();
	
	void setCountryCode(String countryCode);
}
