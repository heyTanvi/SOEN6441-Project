package com.soen.app.covidtracker.init;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.soen.app.covidtracker.domain.Country;
import com.soen.app.covidtracker.domain.Global;
import com.soen.app.covidtracker.init.impl.CovidApiDBCreateImpl;
import com.soen.app.covidtracker.requests.CovidApiRO;

@SuppressWarnings("deprecation")
public class CovidApiInit {

	private static final String USER_AGENT = "Mozilla/5.0";
	private static final String GET_URL = "https://api.covid19api.com/summary";

	private static final ObjectMapper MAPPER;
	static {
		MAPPER = new ObjectMapper();
		MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		MAPPER.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
	}

	public CovidApiInit() {

	}

	public void getCovidSummaryFromAPI() throws IOException {
		URL obj = new URL(GET_URL);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("User-Agent", USER_AGENT);
		int responseCode = con.getResponseCode();
		System.out.println("GET Response Code :: " + responseCode);
		if (responseCode == HttpURLConnection.HTTP_OK) { // success
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			CovidApiRO ro = parserJSONToObject(response.toString());
			if (ro != null) {
				new CovidApiDBCreateImpl().persitInDatabase(ro);
			}
		} else {
			System.out.println("GET request not worked");
		}
	}

	private static CovidApiRO parserJSONToObject(String string) {
		try {
			return MAPPER.readValue(string, CovidApiRO.class);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String convertGlobalToString(Global global) {
		try {
			return MAPPER.writeValueAsString(global);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String convertCountriesToString(List<Country> allCountriesResult) {
		try {
			return MAPPER.writeValueAsString(allCountriesResult);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String convertCountryToString(Country result) {
		try {
			return MAPPER.writeValueAsString(result);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}

}