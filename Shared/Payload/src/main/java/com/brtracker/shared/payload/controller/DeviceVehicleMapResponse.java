package com.brtracker.shared.payload.controller;

import java.io.Serializable;
import java.util.List;

import com.brtracker.shared.payload.controller.data.DeviceVehicleMap;
import com.brtracker.shared.payload.reporting.AbstractSearchResponse;

public class DeviceVehicleMapResponse extends AbstractSearchResponse implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<DeviceVehicleMap> deviceVehicleMapList;

	public List<DeviceVehicleMap> getDeviceVehicleMapList() {
		return deviceVehicleMapList;
	}

	public void setDeviceVehicleMapList(List<DeviceVehicleMap> deviceVehicleMapList) {
		this.deviceVehicleMapList = deviceVehicleMapList;
	}
	
	

}
