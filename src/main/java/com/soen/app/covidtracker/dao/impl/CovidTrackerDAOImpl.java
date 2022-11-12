package com.soen.app.covidtracker.dao.impl;

import java.util.List;

import com.soen.app.covidtracker.dao.CovidTrackerDAO;
import com.soen.app.covidtracker.domain.Country;
import com.soen.app.covidtracker.domain.Global;
import com.soen.app.covidtracker.init.CovidApiDB;
import com.soen.app.covidtracker.init.impl.CovidApiDBReadImpl;

public class CovidTrackerDAOImpl implements CovidTrackerDAO {

	private CovidApiDB covidApiDB;

	public CovidTrackerDAOImpl() {
		covidApiDB = new CovidApiDBReadImpl();
	}

	@Override
	public Global getAllGlobalResult() {
		return covidApiDB.getAllGlobalResult();
	}

	@Override
	public List<Country> getAllCountriesResult() {
		return covidApiDB.getAllCountriesResult();
	}

	@Override
	public List<String> getAllCountryCodes() {
		return covidApiDB.getAllCountryCodes();
	}

	@Override
	public Country getCountryResult(String code) {
		return covidApiDB.getCountryResult(code);
	}

	@Override
	public List<Country> getAllCountryResultByCode(String codes) {
		return covidApiDB.getAllCountryResultByCode(codes);
	}

}
