package com.soen.app.covidtracker.init.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.soen.app.covidtracker.domain.Country;
import com.soen.app.covidtracker.domain.Global;
import com.soen.app.covidtracker.domain.impl.CountryEO;
import com.soen.app.covidtracker.domain.impl.GlobalEO;
import com.soen.app.covidtracker.init.AbstractCovidApiDB;

public class CovidApiDBReadImpl extends AbstractCovidApiDB {

	@Override
	public Global getAllGlobalResult() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(URI, U_NAME, PASSWORD);

			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(GLOBAL_SELECT_SQL);

			if (result.next()) {
				Global global = new GlobalEO();
				global.setGlobal(true);
				global.setNewConfirmed(result.getInt("newConfirmed"));
				global.setTotalConfirmed(result.getInt("totalConfirmed"));
				global.setNewDeaths(result.getInt("newDeaths"));
				global.setTotalDeaths(result.getInt("totalDeaths"));
				global.setNewRecovered(result.getInt("newRecovered"));
				global.setTotalDeaths(result.getInt("totalRecovered"));

				return global;
			}

		} catch (SQLException e) {
			System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<String> getAllCountryCodes() {

		return null;
	}

	@Override
	public Country getCountryResult(String code) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(URI, U_NAME, PASSWORD);

			PreparedStatement prepareStatement = conn.prepareStatement(COUNTRY_SELECT_SQL_BY_CODE);
			prepareStatement.setString(1, code);
			ResultSet result = prepareStatement.executeQuery();
			if (result.next()) {
				Country country = new CountryEO();
				country.setGlobal(false);
				country.setNewConfirmed(result.getInt("newConfirmed"));
				country.setTotalConfirmed(result.getInt("totalConfirmed"));
				country.setNewDeaths(result.getInt("newDeaths"));
				country.setTotalDeaths(result.getInt("totalDeaths"));
				country.setNewRecovered(result.getInt("newRecovered"));
				country.setTotalDeaths(result.getInt("totalRecovered"));
				country.setCountryCode(result.getString("countryCode"));
				country.setName(result.getString("name"));
				return country;
			}

		} catch (SQLException e) {
			System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	@Override
	public List<Country> getAllCountriesResult() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(URI, U_NAME, PASSWORD);

			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(COUNTRY_SELECT_SQL);
			List<Country> countires = new ArrayList<>();
			while (result.next()) {
				Country country = new CountryEO();
				country.setGlobal(false);
				country.setNewConfirmed(result.getInt("newConfirmed"));
				country.setTotalConfirmed(result.getInt("totalConfirmed"));
				country.setNewDeaths(result.getInt("newDeaths"));
				country.setTotalDeaths(result.getInt("totalDeaths"));
				country.setNewRecovered(result.getInt("newRecovered"));
				country.setTotalDeaths(result.getInt("totalRecovered"));
				country.setCountryCode(result.getString("countryCode"));
				country.setName(result.getString("name"));
				countires.add(country);
			}
			return countires;

		} catch (SQLException e) {
			System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Country> getAllCountryResultByCode(String countryCodes) {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(URI, U_NAME, PASSWORD);

			PreparedStatement prepareStatement = conn.prepareStatement(COUNTRY_SELECT_SQL_BY_CODES);
			String[] split = countryCodes.split(",");
			for (int i = 1; i <= 8; i++) {
				prepareStatement.setString(i, split[i - 1]);
			}

			ResultSet result = prepareStatement.executeQuery();

			List<Country> countires = new ArrayList<>();
			while (result.next()) {
				Country country = new CountryEO();
				country.setGlobal(false);
				country.setNewConfirmed(result.getInt("newConfirmed"));
				country.setTotalConfirmed(result.getInt("totalConfirmed"));
				country.setNewDeaths(result.getInt("newDeaths"));
				country.setTotalDeaths(result.getInt("totalDeaths"));
				country.setNewRecovered(result.getInt("newRecovered"));
				country.setTotalDeaths(result.getInt("totalRecovered"));
				country.setCountryCode(result.getString("countryCode"));
				country.setName(result.getString("name"));
				countires.add(country);
			}
			return countires;

		} catch (SQLException e) {
			System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
