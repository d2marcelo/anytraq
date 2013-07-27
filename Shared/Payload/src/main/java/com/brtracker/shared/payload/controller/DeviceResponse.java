package com.brtracker.shared.payload.controller;

import java.io.Serializable;
import java.util.List;

import com.brtracker.shared.payload.controller.data.Device;
import com.brtracker.shared.payload.reporting.AbstractSearchResponse;

public class DeviceResponse extends AbstractSearchResponse implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private List<Device> deviceList;
	
	public List<Device> getDeviceList() {
		return deviceList;
	}
	public void setDeviceList(List<Device> deviceList) {
		this.deviceList = deviceList;
	}
	
	
	

}
