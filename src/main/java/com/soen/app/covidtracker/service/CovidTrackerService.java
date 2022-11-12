package com.soen.app.covidtracker.service;

public interface CovidTrackerService {
	String getAllGlobalResult();

	String getAllCountryResult();
	
	String getAllCountryCode();

	String getCountryResult(String code);

	String getAllCountryResultByCode(String codes);

}
