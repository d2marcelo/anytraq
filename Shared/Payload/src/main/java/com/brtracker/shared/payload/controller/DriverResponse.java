package com.brtracker.shared.payload.controller;

import java.io.Serializable;
import java.util.List;

import com.brtracker.shared.payload.controller.data.Driver;
import com.brtracker.shared.payload.reporting.AbstractSearchResponse;

public class DriverResponse extends AbstractSearchResponse implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private List<Driver> driverList;
	
	public List<Driver> getDriverList() {
		return driverList;
	}
	public void setDriverList(List<Driver> driverList) {
		this.driverList = driverList;
	}
	
	

}
