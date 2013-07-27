package com.brtracker.services.appapi;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AppAPISuite extends TestSuite {
	
	public static Test suite() {
		TestSuite suite = new TestSuite();
		//suite.addTestSuite(VehicleTest.class);
		//suite.addTestSuite(DeviceTest.class);
		//suite.addTestSuite(DriverTest.class);
		//suite.addTestSuite(UserTest.class);
		//suite.addTestSuite(TripTest.class);
		//suite.addTestSuite(AccountTest.class);
		suite.addTestSuite(GraphDataTest.class);
		return suite;
	}

}
