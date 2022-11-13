package com.soen.app.covidtracker;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.soen.app.covidtracker.init.CovidApiInit;
import com.soen.app.covidtracker.service.CovidTrackerService;
import com.soen.app.covidtracker.service.impl.CovidTrackerServiceImpl;

@WebServlet("/register")
public class CovidTrackerApplication extends HttpServlet {

	private CovidTrackerService covidTrackerService;

	public void init() throws ServletException {
		System.out.println("----------");
		System.out.println("---------- Server Initialized successfully ----------");
		System.out.println("----------");

		setUpCovidData();
	}

	private void setUpCovidData() {
		try {
			CovidApiInit covidApiInit = new CovidApiInit();
			covidApiInit.getCovidSummaryFromAPI();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();

		String filter = request.getParameter("filter");
		covidTrackerService = new CovidTrackerServiceImpl();
		if (filter.equals("global")) {
			out.print(covidTrackerService.getAllGlobalResult());
		} else if (filter.equals("country")) {
			String code = request.getParameter("code");
			out.print(covidTrackerService.getCountryResult(code));
		} else if (filter.equals("allCountry")) {
			out.print(covidTrackerService.getAllCountryResult());
		} else if (filter.equals("analysis")) {
			String codes = request.getParameter("codes");
			out.print(covidTrackerService.getAllCountryResultByCode(codes));
		}
		out.close();
	}

}
