package com.brtracker.shared.payload.controller;

import java.util.List;

import com.brtracker.shared.payload.controller.data.Device;
import com.brtracker.shared.payload.controller.data.DriverVehicleMap;

public class DriverVehicleDeviceMap {

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
}
