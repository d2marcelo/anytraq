package com.brtracker.shared.payload.controller;

import java.io.Serializable;
import java.util.List;

import com.brtracker.shared.payload.controller.data.DriverVehicleMap;
import com.brtracker.shared.payload.reporting.AbstractSearchResponse;

public class DriverVehicleMapResponse extends AbstractSearchResponse implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private List<DriverVehicleMap> driverVehicleMapList;
 
	public List<DriverVehicleMap> getDriverVehicleMapList() {
		return driverVehicleMapList;
	}

	public void setDriverVehicleMapList(List<DriverVehicleMap> driverVehicleMapList) {
		this.driverVehicleMapList = driverVehicleMapList;
	}

	
	
	
	
	
	
	
	
	
	

}
