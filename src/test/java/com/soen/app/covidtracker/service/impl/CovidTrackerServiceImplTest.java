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
	

}
