package com.brtracker.shared.payload.controller;

import java.util.List;

import com.brtracker.shared.payload.controller.data.Device;

public class VehicleDeviceRequest extends VehicleRequest {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Device> deviceList;
	
	public List<Device> getDeviceList() {
		return deviceList;
	}
	public void setDeviceList(List<Device> deviceList) {
		this.deviceList = deviceList;
	}

	
	
	
}
