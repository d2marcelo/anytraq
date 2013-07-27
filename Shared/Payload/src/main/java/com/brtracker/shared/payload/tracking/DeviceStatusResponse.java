package com.brtracker.shared.payload.tracking;

import java.io.Serializable;
import java.util.List;

import com.brtracker.shared.payload.reporting.AbstractSearchResponse;
import com.brtracker.shared.payload.tracking.data.DeviceStatus;

public class DeviceStatusResponse extends AbstractSearchResponse implements Serializable {

	private List<DeviceStatus> deviceStatusList;

	public List<DeviceStatus> getDeviceStatusList() {
		return deviceStatusList;
	}

	public void setDeviceStatusList(List<DeviceStatus> deviceStatusList) {
		this.deviceStatusList = deviceStatusList;
	}
	
	
}
