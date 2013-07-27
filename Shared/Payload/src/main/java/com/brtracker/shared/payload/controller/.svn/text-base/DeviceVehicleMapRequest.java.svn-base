package com.brtracker.shared.payload.controller;

import com.brtracker.shared.payload.controller.data.DeviceVehicleMap;
import com.brtracker.shared.payload.reporting.AbstractSearchCriteria;

public class DeviceVehicleMapRequest extends AbstractSearchCriteria {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DeviceVehicleMap deviceVehicleMap;
	
	
	

	public DeviceVehicleMap getDeviceVehicleMap() {
		return deviceVehicleMap;
	}

	public void setDeviceVehicleMap(DeviceVehicleMap deviceVehicleMap) {
		this.deviceVehicleMap = deviceVehicleMap;
	}

	@Override
	public String toString(){
		StringBuffer b = new StringBuffer();
		if (deviceVehicleMap != null)
		b.append(this.getDeviceVehicleMap().toString());
		b.append(super.toString());
		return b.toString();
	}
	
	

}
