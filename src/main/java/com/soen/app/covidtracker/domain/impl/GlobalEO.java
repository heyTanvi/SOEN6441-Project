package com.soen.app.covidtracker.domain.impl;

import com.soen.app.covidtracker.domain.Global;

public class GlobalEO extends DomainEO implements Global {

	private int newConfirmed;
	private int totalConfirmed;
	private int newDeaths;
	private int totalDeaths;
	private int newRecovered;
	private int totalRecovered;
	private boolean isGlobal;

	public GlobalEO() {

	}

	@Override
	public int getNewConfirmed() {
		return newConfirmed;
	}

	@Override
	public void setNewConfirmed(int newConfirmed) {
		this.newConfirmed = newConfirmed;
	}

	@Override
	public int getTotalConfirmed() {
		return totalConfirmed;
	}

	@Override
	public void setTotalConfirmed(int totalConfirmed) {
		this.totalConfirmed = totalConfirmed;
	}

	@Override
	public int getNewDeaths() {
		return newDeaths;
	}

	@Override
	public void setNewDeaths(int newDeaths) {
		this.newDeaths = newDeaths;
	}

	@Override
	public int getTotalDeaths() {
		return totalDeaths;
	}

	@Override
	public void setTotalDeaths(int totalDeaths) {
		this.totalDeaths = totalDeaths;
	}

	@Override
	public int getNewRecovered() {
		return newRecovered;
	}

	@Override
	public void setNewRecovered(int newRecovered) {
		this.newRecovered = newRecovered;
	}

	@Override
	public int getTotalRecovered() {
		return totalRecovered;
	}

	@Override
	public void setTotalRecovered(int totalRecovered) {
		this.totalRecovered = totalRecovered;
	}

	@Override
	public boolean isGlobal() {
		return isGlobal;
	}

	@Override
	public void setGlobal(boolean isGlobal) {
		this.isGlobal = isGlobal;
	}

}