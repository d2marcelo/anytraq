package com.brtracker.services.appapi;

import com.brtracker.shared.payload.controller.ControllerInterfaceLookup;
import com.brtracker.shared.payload.controller.lookup.ServiceType;
import com.brtracker.shared.payload.tracking.TrackingInterfaceLookup;
import com.brtracker.shared.utils.logging.MyLogger;
import com.brtracker.shared.utils.wsutils.ClientCall;
import com.brtracker.shared.utils.wsutils.ServiceException;
import com.brtracker.shared.utils.wsutils.ServiceResponseException;

public class VehicleManager {

	private String TRACKING_IP = ServiceLookup.getTrackingIP(ServiceType.TRACKING);
	private String CONTROLLER_IP = ServiceLookup.getControllerIP(ServiceType.CONTROLLER);
	
private MyLogger logger = new MyLogger(VehicleManager.class);

	public String add (String payload) {
	try {
		return ClientCall.post(ControllerInterfaceLookup.getURLPath(ControllerInterfaceLookup.vehicle_ADD,  CONTROLLER_IP), payload);
	} catch (ServiceException e) {
		return ServiceResponseException.generateResponse(e);
	}
	}
	
	public String addVehicleDeviceMap (String payload) {
		try {
			return ClientCall.post(ControllerInterfaceLookup.getURLPath(ControllerInterfaceLookup.vehicle_ADD_VEHICLE_DEVICE_MAP,  CONTROLLER_IP), payload);
		} catch (ServiceException e) {
			return ServiceResponseException.generateResponse(e);
		}
		}
	
	public String updateVehicleDeviceMap (String payload) {
		try {
			return ClientCall.post(ControllerInterfaceLookup.getURLPath(ControllerInterfaceLookup.vehicle_UPDATE_VEHICLE_DEVICE_MAP,  CONTROLLER_IP), payload);
		} catch (ServiceException e) {
			return ServiceResponseException.generateResponse(e);
		}
		}
	
	public String update (String payload) {
		try {
			return ClientCall.post(ControllerInterfaceLookup.getURLPath(ControllerInterfaceLookup.vehicle_UPDATE,  CONTROLLER_IP), payload);
		} catch (ServiceException e) {
			return ServiceResponseException.generateResponse(e);
		}
	}
	
	public String delete (String payload) {
		try {
			return ClientCall.post(ControllerInterfaceLookup.getURLPath(ControllerInterfaceLookup.vehicle_DELETE,  CONTROLLER_IP), payload);
		} catch (ServiceException e) {
			return ServiceResponseException.generateResponse(e);
		}
	}
	public String list (String payload) {
		try {
			return ClientCall.post(ControllerInterfaceLookup.getURLPath(ControllerInterfaceLookup.vehicle_LIST,  CONTROLLER_IP), payload);
		} catch (ServiceException e) {
			return ServiceResponseException.generateResponse(e);
		}
	}
	
	public String status (Long accountId) {
		try {
			String url = TrackingInterfaceLookup.getURLPath(TrackingInterfaceLookup.vehicleStatus_LIST,  TRACKING_IP);
			String endpoint = url + "?accountId="+accountId;
			logger.logInfo("calling :"+endpoint);
			return ClientCall.get(endpoint);
		} catch (ServiceException e) {
			return ServiceResponseException.generateResponse(e);
		}
	}
	
	public String get(String licensePlate) {
		try {
			String url = ControllerInterfaceLookup.getURLPath(ControllerInterfaceLookup.vehicle_GET, CONTROLLER_IP);
			String endpoint = url +"?uniqueKey="+licensePlate;
			return ClientCall.get(endpoint);
		} catch (ServiceException e) {
			return ServiceResponseException.generateResponse(e);
		}
	}
	
}
