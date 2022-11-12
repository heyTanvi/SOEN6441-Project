package com.soen.app.covidtracker.init.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.soen.app.covidtracker.domain.impl.CountryEO;
import com.soen.app.covidtracker.domain.impl.GlobalEO;
import com.soen.app.covidtracker.init.AbstractCovidApiDB;
import com.soen.app.covidtracker.requests.CovidApiRO;

public class CovidApiDBCreateImpl extends AbstractCovidApiDB {

	private static final int MAX_COUNTRIES = 20;

	@Override
	public void persitInDatabase(CovidApiRO ro) {
		persistGlobalTable(ro.getGlobal());
		persistCountriesTable(ro.getCountries());
	}

	private void persistCountriesTable(List<CountryEO> countries) {
		if (countries != null && !countries.isEmpty()) {
			if (countries.size() > 20) {
				for (int i = 0; i < MAX_COUNTRIES; i++) {
					persistCountryTable(countries.get(i));
				}
			}
		}

	}

	private void persistCountryTable(CountryEO eo) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(URI, U_NAME, PASSWORD);

			PreparedStatement preparedStatement = conn.prepareStatement(GLOBAL_INSERT_SQL,
					Statement.RETURN_GENERATED_KEYS);

			preparedStatement.setLong(1, eo.getNewConfirmed());
			preparedStatement.setLong(2, eo.getTotalConfirmed());
			preparedStatement.setLong(3, eo.getNewDeaths());
			preparedStatement.setLong(4, eo.getTotalDeaths());
			preparedStatement.setLong(5, eo.getNewRecovered());
			preparedStatement.setLong(6, eo.getTotalRecovered());
			preparedStatement.setBoolean(7, false);

			preparedStatement.executeUpdate();
			ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
			if (generatedKeys.next()) {
				int globalId = generatedKeys.getInt(1);
				preparedStatement = conn.prepareStatement(COUNTRY_INSERT_SQL);

				preparedStatement.setString(1, eo.getName());
				preparedStatement.setString(2, eo.getCountryCode());
				preparedStatement.setLong(3, globalId);
				preparedStatement.executeUpdate();
			}

		} catch (SQLException e) {
			System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void persistGlobalTable(GlobalEO eo) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(URI, U_NAME, PASSWORD);

			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(GLOBAL_SELECT_SQL);

			if (!result.next()) {

				PreparedStatement preparedStatement = conn.prepareStatement(GLOBAL_INSERT_SQL);

				preparedStatement.setLong(1, eo.getNewConfirmed());
				preparedStatement.setLong(2, eo.getTotalConfirmed());
				preparedStatement.setLong(3, eo.getNewDeaths());
				preparedStatement.setLong(4, eo.getTotalDeaths());
				preparedStatement.setLong(5, eo.getNewRecovered());
				preparedStatement.setLong(6, eo.getTotalRecovered());
				preparedStatement.setBoolean(7, true);

				preparedStatement.executeUpdate();
			}

		} catch (SQLException e) {
			System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
