package com.brtracker.services.tracking;

import java.util.Date;

import com.brtracker.shared.payload.tracking.lookup.UnitType;

public class FuelGraphDataHandler extends DeviceGraphData {

	private Float previousDistance = new Float(0);
	
	

	@Override
	public String getXunit() {
		return UnitType.LITER;
	}

	@Override
	public String getYunit() {
		return UnitType.KILOMETER;
	}

	@Override
	public String processX(String x) {
		Float currentDistance = Float.parseFloat(x);
		if (previousDistance.equals(new Float(0))) {
			previousDistance = currentDistance;
			return "0";
		}
		Float total = (currentDistance - previousDistance) / 1000;
		return total.toString();
	}

	@Override
	public String processY(String y) {
		return y;
	}

	@Override
	public void add(String x, String y, String latitude, String longitude,
			Date date) {
		// TODO Auto-generated method stub
		
	}

}
