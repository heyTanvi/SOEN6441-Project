package com.soen.app.covidtracker.init;

import java.util.List;

import com.soen.app.covidtracker.domain.Country;
import com.soen.app.covidtracker.domain.Global;
import com.soen.app.covidtracker.requests.CovidApiRO;

public interface CovidApiDB {

	String URI = "jdbc:mysql://localhost:3306/covid_tracker?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=GMT&useSSL=false&allowPublicKeyRetrieval=true";
	String U_NAME = "root";
	String PASSWORD = "root";

	void persitInDatabase(CovidApiRO ro);

	Global getAllGlobalResult();

	List<Country> getAllCountriesResult();

	List<String> getAllCountryCodes();

	Country getCountryResult(String code);

	List<Country> getAllCountryResultByCode(String codes);

}
