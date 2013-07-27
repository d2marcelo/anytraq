package com.brtracker.shared.payload.tracking;

import java.io.Serializable;
import java.util.List;
import com.brtracker.shared.payload.reporting.AbstractSearchResponse;
import com.brtracker.shared.payload.tracking.data.VehicleStatus;


public class VehicleStatusResponse extends AbstractSearchResponse implements Serializable {

	private List<VehicleStatus> vehicleStatusList;

	public List<VehicleStatus> getVehicleStatusList() {
		return vehicleStatusList;
	}

	public void setVehicleStatusList(List<VehicleStatus> vehicleStatusList) {
		this.vehicleStatusList = vehicleStatusList;
	}

	
}
