package com.brtracker.shared.payload.controller;

import java.util.List;

import com.brtracker.shared.payload.controller.data.Device;
import com.brtracker.shared.payload.controller.data.DriverVehicleMap;
import com.brtracker.shared.payload.reporting.AbstractSearchCriteria;

public class DriverVehicleMapRequest extends AbstractSearchCriteria {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DriverVehicleMap driverVehicleMap;
	private List<Device> deviceList;
	
	public DriverVehicleMap getDriverVehicleMap() {
		return driverVehicleMap;
	}

	public void setDriverVehicleMap(DriverVehicleMap driverVehicleMap) {
		this.driverVehicleMap = driverVehicleMap;
	}

	public List<Device> getDeviceList() {
		return deviceList;
	}

	public void setDeviceList(List<Device> deviceList) {
		this.deviceList = deviceList;
	}
	
	@Override
	public String toString(){
		StringBuffer b = new StringBuffer();
		if (driverVehicleMap != null)
		b.append(this.getDriverVehicleMap().toString());
		b.append(super.toString());
		return b.toString();
	}
	
	

}
