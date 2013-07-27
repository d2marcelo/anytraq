package com.brtracker.shared.utils.gps;

import junit.framework.Assert;
import junit.framework.TestCase;

public class GpsUtilsTest extends TestCase {
	
	public void testAngleToCourse() {
		
		Assert.assertEquals("Invalid N heading", GpsUtils.angleToCourse(85), "N");
		Assert.assertEquals("Invalid N heading", GpsUtils.angleToCourse(90), "N");
		Assert.assertEquals("Invalid N heading", GpsUtils.angleToCourse(95), "N");
		
		Assert.assertEquals("Invalid NE heading", GpsUtils.angleToCourse(40), "NE");
		Assert.assertEquals("Invalid NE heading", GpsUtils.angleToCourse(45), "NE");
		Assert.assertEquals("Invalid NE heading", GpsUtils.angleToCourse(50), "NE");

		Assert.assertEquals("Invalid E heading", GpsUtils.angleToCourse(355), "E");
		Assert.assertEquals("Invalid E heading", GpsUtils.angleToCourse(360), "E");
		Assert.assertEquals("Invalid E heading", GpsUtils.angleToCourse(0), "E");
		Assert.assertEquals("Invalid E heading", GpsUtils.angleToCourse(5), "E");
		
		Assert.assertEquals("Invalid SE heading", GpsUtils.angleToCourse(310), "SE");
		Assert.assertEquals("Invalid SE heading", GpsUtils.angleToCourse(315), "SE");
		Assert.assertEquals("Invalid SE heading", GpsUtils.angleToCourse(320), "SE");

		Assert.assertEquals("Invalid S heading", GpsUtils.angleToCourse(265), "S");
		Assert.assertEquals("Invalid S heading", GpsUtils.angleToCourse(270), "S");
		Assert.assertEquals("Invalid S heading", GpsUtils.angleToCourse(275), "S");

		Assert.assertEquals("Invalid SW heading", GpsUtils.angleToCourse(220), "SW");
		Assert.assertEquals("Invalid SW heading", GpsUtils.angleToCourse(225), "SW");
		Assert.assertEquals("Invalid SW heading", GpsUtils.angleToCourse(230), "SW");

		Assert.assertEquals("Invalid W heading", GpsUtils.angleToCourse(175), "W");
		Assert.assertEquals("Invalid W heading", GpsUtils.angleToCourse(180), "W");
		Assert.assertEquals("Invalid W heading", GpsUtils.angleToCourse(185), "W");

		Assert.assertEquals("Invalid NW heading", GpsUtils.angleToCourse(130), "NW");
		Assert.assertEquals("Invalid NW heading", GpsUtils.angleToCourse(135), "NW");
		Assert.assertEquals("Invalid NW heading", GpsUtils.angleToCourse(140), "NW");

	}

}
