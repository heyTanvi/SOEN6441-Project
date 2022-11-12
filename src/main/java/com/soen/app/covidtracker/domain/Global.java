package com.soen.app.covidtracker.domain;

public interface Global {
	int getNewConfirmed();

	void setNewConfirmed(int newConfirmed);

	int getTotalConfirmed();

	void setTotalConfirmed(int totalConfirmed);

	int getNewDeaths();

	void setNewDeaths(int newDeaths);

	int getTotalDeaths();

	void setTotalDeaths(int totalDeaths);

	int getNewRecovered();

	void setNewRecovered(int newRecovered);

	int getTotalRecovered();

	void setTotalRecovered(int totalRecovered);

	boolean isGlobal();

	void setGlobal(boolean isGlobal);
}
