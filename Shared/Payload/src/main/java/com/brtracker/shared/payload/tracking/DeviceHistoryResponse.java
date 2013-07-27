package com.brtracker.shared.payload.tracking;

import java.io.Serializable;
import java.util.List;

import com.brtracker.shared.payload.reporting.AbstractSearchResponse;
import com.brtracker.shared.payload.tracking.data.DeviceHistory;

public class DeviceHistoryResponse  extends AbstractSearchResponse implements Serializable {
	private static final long serialVersionUID = 1L;

	private List<DeviceHistory> deviceHistoryList;

	public List<DeviceHistory> getDeviceHistoryList() {
		return deviceHistoryList;
	}

	public void setDeviceHistoryList(List<DeviceHistory> deviceHistoryList) {
		this.deviceHistoryList = deviceHistoryList;
	}
	
	
	
}