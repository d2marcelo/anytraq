package com.brtracker.services.async;

import java.sql.Timestamp;

public class TripSummaryCalculator {

	
	
	
	public String getTotalFuel (String lastReading, String currentReading) {
		if (lastReading == null) return currentReading;
		Float last = Float.parseFloat(lastReading);
		Float curr = Float.parseFloat(currentReading);
		Float total =  (last + curr);
		return total.toString();
	}
	
	public String getTotalFuelPerKm (String lastReading, String currentReading) {
		if (lastReading == null) return currentReading;
		Float last = Float.parseFloat(lastReading);
		Float curr = Float.parseFloat(currentReading);
		Float total =  (last + curr);
		return total.toString();
	}
	
	public String getTotalIdle (Timestamp currentTime, Timestamp previousTime) {
		if (previousTime == null) return "0";
		long diff = currentTime.getTime() - previousTime.getTime();
		long total = (diff / (60 * 1000));
		return new Long(total).toString();
	}
}
