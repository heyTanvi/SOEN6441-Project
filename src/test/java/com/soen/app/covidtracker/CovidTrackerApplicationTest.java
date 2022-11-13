package com.soen.app.covidtracker;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.junit.Test;

public class CovidTrackerApplicationTest {

	private CovidTrackerApplication covidTrackerApplication;

	private HttpServletRequest req;

	@Test
	public void test01() throws IOException, ServletException {
		covidTrackerApplication = new CovidTrackerApplication();
	}

}
