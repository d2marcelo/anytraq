package com.brtracker.shared.payload.tracking;

import com.brtracker.shared.payload.reporting.AbstractSearchCriteria;
import com.brtracker.shared.payload.tracking.data.VehicleStatus;

public class VehicleStatusRequest  extends AbstractSearchCriteria {
	
	private VehicleStatus vehicleStatus;
	private Long accountId;
	
	public VehicleStatus getVehicleStatus() {
		return vehicleStatus;
	}

	public void setVehicleStatus(VehicleStatus vehicleStatus) {
		this.vehicleStatus = vehicleStatus;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	
	

	
}
