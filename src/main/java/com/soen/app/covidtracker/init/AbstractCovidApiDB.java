package com.soen.app.covidtracker.init;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import com.soen.app.covidtracker.domain.Country;
import com.soen.app.covidtracker.domain.Global;
import com.soen.app.covidtracker.requests.CovidApiRO;

public class AbstractCovidApiDB implements CovidApiDB {

	protected static final String GLOBAL_INSERT_SQL = "INSERT INTO GLOBAL (newConfirmed, totalConfirmed, newDeaths, totalDeaths, newRecovered, totalRecovered, isGlobal) VALUES (?,?,?,?,?,?,?)";
	protected static final String GLOBAL_SELECT_SQL = "SELECT * FROM GLOBAL where isGlobal = true";

	protected static final String COUNTRY_INSERT_SQL = "INSERT INTO COUNTRY (name, countryCode, globalId) VALUES (?,?,?)";
	protected static final String COUNTRY_SELECT_SQL = "select * from COUNTRY INNER JOIN GLOBAL ON COUNTRY.globalId = GLOBAL.global_id";
	protected static final String COUNTRY_SELECT_SQL_BY_CODE = "select * from COUNTRY INNER JOIN GLOBAL ON COUNTRY.globalId = GLOBAL.global_id where countryCode = ?";
	protected static final String COUNTRY_SELECT_SQL_BY_CODES = "select * from COUNTRY INNER JOIN GLOBAL ON COUNTRY.globalId = GLOBAL.global_id where countryCode in (?,?,?,?,?,?,?,?)";

	private static Connection con = null;

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(URI, U_NAME, PASSWORD);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {
		return con;
	}

	@Override
	public void persitInDatabase(CovidApiRO ro) {
		// TODO Auto-generated method stub

	}

	@Override
	public Global getAllGlobalResult() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Country> getAllCountriesResult() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getAllCountryCodes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Country getCountryResult(String code) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Country> getAllCountryResultByCode(String countryCodes) {
		// TODO Auto-generated method stub
		return null;
	}

}
