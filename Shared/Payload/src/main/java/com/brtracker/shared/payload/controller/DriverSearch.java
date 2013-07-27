package com.brtracker.shared.payload.controller;

import com.brtracker.shared.payload.controller.data.Driver;
import com.brtracker.shared.payload.tracking.data.VehicleStatus;


public class DriverSearch {

	private Driver driver;
	private VehicleStatus vehicleStatus;
	
	
	public Driver getDriver() {
		return driver;
	}
	public void setDriver(Driver driver) {
		this.driver = driver;
	}
	public VehicleStatus getVehicleStatus() {
		return vehicleStatus;
	}
	public void setVehicleStatus(VehicleStatus vehicleStatus) {
		this.vehicleStatus = vehicleStatus;
	}
	
	
	
}
