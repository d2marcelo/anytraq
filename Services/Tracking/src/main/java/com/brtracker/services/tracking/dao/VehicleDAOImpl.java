package com.brtracker.services.tracking.dao;

import java.util.List;
import com.brtracker.shared.payload.tracking.data.VehicleStatus;


public class VehicleDAOImpl extends TrackingDAOImpl {

	@SuppressWarnings("unchecked")
	public List<VehicleStatus> listVehicleStatus (Long accountId) {
		return (List<VehicleStatus>) find("from VehicleStatus where accountId="+accountId);
	}
	
	public VehicleStatus getVehicleStatus (Long deviceId) {
		return (VehicleStatus) super.findUniqueResult("from VehicleStatus where deviceId="+deviceId);
	}
	
	
}
