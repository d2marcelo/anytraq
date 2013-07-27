package com.brtracker.shared.payload.tracking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DriverBehaviorResponse {

	private List<DriverBehaviorDetail> driverBehavior = new ArrayList<DriverBehaviorDetail>();
	private Map<String, Integer> aggregatedTotals= new HashMap<String,Integer>();
	
	
	public void addAggregatedTotal(String key) {
		int count = 0;
		if (aggregatedTotals.containsKey(key)){
			count = aggregatedTotals.get(key);
		}
		count++;
		aggregatedTotals.put(key, count);
	}
	
	public void addDriverBehavior (String key, String latitude, String longitude) {
		DriverBehaviorDetail db = new DriverBehaviorDetail();
		db.setAttribute(key);
		db.setLatitude(latitude);
		db.setLongitude(longitude);
		driverBehavior.add(db);
		
	}
	public List<DriverBehaviorDetail> getDriverBehavior() {
		return driverBehavior;
	}
	public void setDriverBehavior(List<DriverBehaviorDetail> driverBehavior) {
		this.driverBehavior = driverBehavior;
	}
	public Map<String, Integer> getAggregatedTotals() {
		return aggregatedTotals;
	}
	public void setAggregatedTotals(Map<String, Integer> aggregatedTotals) {
		this.aggregatedTotals = aggregatedTotals;
	}
	
	
	
	
}
