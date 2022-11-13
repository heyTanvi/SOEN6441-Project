package com.soen.app.covidtracker.service.impl;

import java.util.List;

import com.soen.app.covidtracker.dao.CovidTrackerDAO;
import com.soen.app.covidtracker.dao.impl.CovidTrackerDAOImpl;
import com.soen.app.covidtracker.domain.Country;
import com.soen.app.covidtracker.domain.Global;
import com.soen.app.covidtracker.init.CovidApiInit;
import com.soen.app.covidtracker.service.CovidTrackerService;

public class CovidTrackerServiceImpl implements CovidTrackerService {

	private CovidTrackerDAO covidTrackerDAO;

	public CovidTrackerServiceImpl() {
		covidTrackerDAO = new CovidTrackerDAOImpl();
	}

	@Override
	public String getAllGlobalResult() {
		Global allGlobalResult = covidTrackerDAO.getAllGlobalResult();
		return CovidApiInit.convertGlobalToString(allGlobalResult);

	}

	@Override
	public String getAllCountryResult() {
		List<Country> allCountriesResult = covidTrackerDAO.getAllCountriesResult();
		return CovidApiInit.convertCountriesToString(allCountriesResult);
	}

	@Override
	public String getAllCountryCode() {
		List<String> allCountriesResult = covidTrackerDAO.getAllCountryCodes();
		return "";
	}

	@Override
	public String getCountryResult(String code) {
		Country result = covidTrackerDAO.getCountryResult(code);
		return CovidApiInit.convertCountryToString(result);
	}

	@Override
	public String getAllCountryResultByCode(String codes) {
		List<Country> allCountriesResult = covidTrackerDAO.getAllCountryResultByCode(codes);
		return CovidApiInit.convertCountriesToString(allCountriesResult);
	}

}
