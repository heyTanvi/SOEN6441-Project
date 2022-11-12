package com.soen.app.covidtracker.dao;

import java.util.List;

import com.soen.app.covidtracker.domain.Country;
import com.soen.app.covidtracker.domain.Global;

public interface CovidTrackerDAO {

	Global getAllGlobalResult();

	List<Country> getAllCountriesResult();

	List<String> getAllCountryCodes();

	Country getCountryResult(String code);

	List<Country> getAllCountryResultByCode(String codes);

}
