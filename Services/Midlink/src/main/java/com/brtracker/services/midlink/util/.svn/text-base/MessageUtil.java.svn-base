package com.brtracker.services.midlink.util;

import com.javadocmd.simplelatlng.LatLng;
import com.javadocmd.simplelatlng.LatLngTool;
import com.javadocmd.simplelatlng.util.LengthUnit;

public class MessageUtil {

	/** function  converBatValue
	 * A simple function that computes the battery level as a  
	 * function of the value in the Battery Conversion rule (field #249) 
	 */
	public static String converBatValue(int batConversion, int rawVal) {
		switch(batConversion) {
		case -1:
			return "Bat conversion was not set";
		case 0:
			return (rawVal == 255)?"External Power":"Low";
		case 1:
			return "" + (float)rawVal/14;
		case 2:
			return "" + ((float)rawVal/14 + (float)0.7);
		case 3:
			return "" + ((float)rawVal)/51;
		default:
			return "Unknown Battery conversion: " + batConversion;			
		}
	}
	
	/**
	 * function  strExtendedPowerState
	 * A simple function that returns the string representation 
	 * of a specific power state value 
	 */
	public static String strExtendedPowerState(int powerState) {
		switch(powerState) {
		case 0:
			return "No external power";
		case 4:
			return "External power (ACIN)";
		case 8:
			return "Power by USB";
		case 12:
			return "External power (ACIN) and by USB";
		default:
			return "Unknown Extended Power State: " + powerState;
		}
	}

	/*
	 * Using the haversine distance formula, please refer to:
	 * http://www.movable-type.co.uk/scripts/latlong.html
	 */
	public static double distanceBetweenPoints(Double x1, Double y1, Double x2, Double y2) {
		double R = 6371;
		double dLat = Math.toRadians(x2-x1);
		double dLng = Math.toRadians(y2-y1);
		double lat1 = Math.toRadians(x1);
		double lat2 = Math.toRadians(x2);
		double sinLatHalf = Math.sin(dLat/2);
		double sinLngHalf = Math.sin(dLng/2);
		double a = sinLatHalf * sinLatHalf + sinLngHalf * sinLngHalf * Math.cos(lat1) * Math.cos(lat2); 
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		double d = R * c;
		return d;
	}

	public static double distanceInMeters(Double lat1, Double lng1, Double lat2, Double lng2) {
		LatLng point1 = new LatLng(lat1, lng1);
		LatLng point2 = new LatLng(lat2, lng2);
		return LatLngTool.distance(point1, point2, LengthUnit.METER);
	}
}
