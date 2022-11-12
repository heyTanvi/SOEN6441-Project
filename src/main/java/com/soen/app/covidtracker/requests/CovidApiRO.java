package com.soen.app.covidtracker.requests;

import java.util.List;

import com.soen.app.covidtracker.domain.impl.CountryEO;
import com.soen.app.covidtracker.domain.impl.GlobalEO;

public class CovidApiRO {
	String id;
	String message;
	GlobalEO global;

	public GlobalEO getGlobal() {
		return global;
	}

	public void setGlobal(GlobalEO global) {
		this.global = global;
	}

	public List<CountryEO> getCountries() {
		return countries;
	}

	public void setCountries(List<CountryEO> countries) {
		this.countries = countries;
	}

	List<CountryEO> countries;

	public CovidApiRO() {

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
