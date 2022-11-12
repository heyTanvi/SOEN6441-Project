package com.soen.app.covidtracker.service.impl;

import org.junit.Test;

import junit.framework.Assert;

public class CovidTrackerServiceImplTest {

	private CovidTrackerServiceImpl covidTrackerServiceImpl = new CovidTrackerServiceImpl();

	@Test
	public void testInstanceIsCreated() {
		covidTrackerServiceImpl = new CovidTrackerServiceImpl();
		Assert.assertNotNull(covidTrackerServiceImpl);
	}

	@Test
	public void testGetAllGlobalResult() {
		String allGlobalResult = covidTrackerServiceImpl.getAllGlobalResult();
		Assert.assertNotNull(allGlobalResult);
	}

	@Test
	public void testGetAllCountryResult() {
		String allGlobalResult = covidTrackerServiceImpl.getAllCountryResult();
		Assert.assertNotNull(allGlobalResult);
	}

	@Test
	public void testGetCountryResult() {
		String allGlobalResult = covidTrackerServiceImpl.getCountryResult("AD");
		Assert.assertNotNull(allGlobalResult);
	}

	@Test
	public void testGetAllCountryResultByCode() {
		String allGlobalResult = covidTrackerServiceImpl.getAllCountryResultByCode("AD,AL,AF,AM,BB,BY,BE,BZ");
		Assert.assertNotNull(allGlobalResult);
	}

}
