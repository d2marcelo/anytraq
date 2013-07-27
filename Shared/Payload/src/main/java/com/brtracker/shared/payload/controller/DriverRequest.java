package com.brtracker.shared.payload.controller;

import com.brtracker.shared.payload.controller.data.Driver;
import com.brtracker.shared.payload.reporting.AbstractSearchCriteria;

public class DriverRequest extends AbstractSearchCriteria {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Driver driver;

	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}
	
	
	@Override
	public String toString(){
		StringBuffer b = new StringBuffer();
		if (driver != null)
		b.append(this.getDriver().toString());
		b.append(super.toString());
		return b.toString();
	}
	
}
