package com.brtracker.services.tracking;

import java.util.Date;
import com.brtracker.shared.payload.tracking.lookup.GraphDataSummaryAttributeType;
import com.brtracker.shared.payload.tracking.lookup.UnitType;
import com.brtracker.shared.utils.logging.MyLogger;
import com.brtracker.shared.utils.math.NumberUtil;
public class SpeedGraphDataHandler extends DeviceGraphData {

	private MyLogger logger = new MyLogger(SpeedGraphDataHandler.class);
	private double previousDistance = new Double(0);
	private int totalDistance = 0;
	private long totalDrivingTime=0;
	private Date totalDrivingTimeDiff=null;
	
	private int totalSpeed= 0;
	private int maxSpeed = 0 ;
	private int averageSpeed= 0;
	private int speedCount = 0;
	
	@Override
	public String getXunit() {
		return UnitType.KILOMETER;
	}

	@Override
	public String getYunit() {
		return UnitType.KILOMETER;
	}
	
	@Override
	public String processX(String x) {
		logger.logInfo("processing graphdata X :"+x);
		double currentDistance = 0;
		if (NumberUtil.isDouble(x))
		 currentDistance = Double.parseDouble(x);
		if (NumberUtil.isFloat(x))
			currentDistance = Math.round(Float.parseFloat(x));
		if (previousDistance== 0) {
			previousDistance = currentDistance;
			return "0";
		}
		double total = (currentDistance - previousDistance) / 1000;
		totalDistance =  (int) total;
		return new Double(total).toString();
	}

	@Override
	public String processY(String y) {
		return y;
	}

	@Override
	public void add(String x, String y, String latitude, String longitude,Date date) {
		float s = Float.valueOf(y);
		int speed = (int) s;
		// max speed
		if (speed > maxSpeed) maxSpeed = speed;
		// average speed
		speedCount++;
		totalSpeed = totalSpeed + speed;
		averageSpeed = totalSpeed / speedCount;
		
		if (totalDrivingTimeDiff == null) 
			totalDrivingTimeDiff = date;
		totalDrivingTime = date.getTime() - totalDrivingTimeDiff.getTime();
		long drivingTimevalue= totalDrivingTime/(60 * 60 * 1000);
		String drivingTimeUnitType = UnitType.HOUR;
		if (drivingTimevalue == 0) {
			drivingTimevalue= totalDrivingTime/(60 * 1000);
			drivingTimeUnitType = UnitType.MINUTE;
		}
		super.addAggreagatedTotal(GraphDataSummaryAttributeType.AVERAGE_SPEED, String.valueOf(averageSpeed), UnitType.KILOMETER_PER_HOUR);
		super.addAggreagatedTotal(GraphDataSummaryAttributeType.MAX_SPEED, String.valueOf(maxSpeed), UnitType.KILOMETER_PER_HOUR);
		super.addAggreagatedTotal(GraphDataSummaryAttributeType.TOTAL_DISTANCE, String.valueOf(totalDistance), UnitType.KILOMETER);
		super.addAggreagatedTotal(GraphDataSummaryAttributeType.TOTAL_DRIVING_TIME, String.valueOf(drivingTimevalue), drivingTimeUnitType);
		
		
	}

}
