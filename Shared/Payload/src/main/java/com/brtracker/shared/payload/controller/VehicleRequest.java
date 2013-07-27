package com.brtracker.shared.payload.controller;

import com.brtracker.shared.payload.controller.data.Vehicle;
import com.brtracker.shared.payload.reporting.AbstractSearchCriteria;

public class VehicleRequest extends AbstractSearchCriteria {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Vehicle vehicle;

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}
	
	

	
}
