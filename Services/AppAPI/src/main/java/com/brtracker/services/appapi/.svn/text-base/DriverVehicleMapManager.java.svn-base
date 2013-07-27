package com.brtracker.services.appapi;

import com.brtracker.shared.payload.controller.ControllerInterfaceLookup;
import com.brtracker.shared.payload.controller.lookup.ServiceType;
import com.brtracker.shared.utils.wsutils.ClientCall;
import com.brtracker.shared.utils.wsutils.ServiceException;
import com.brtracker.shared.utils.wsutils.ServiceResponseException;

// TODO: Auto-generated Javadoc
/**
 * The Class DriverVehicleMapManager.
 */
public class DriverVehicleMapManager  {
	
	
	
	private String CONTROLLER_IP = ServiceLookup.getControllerIP(ServiceType.CONTROLLER);
	

	/**
	 * Adds the.
	 *
	 * @param payload the payload
	 * @return the string
	 */
	public String add (String payload) {
		try {
			return ClientCall.post(ControllerInterfaceLookup.getURLPath(ControllerInterfaceLookup.driverVehicleMap_ADD,  CONTROLLER_IP), payload);
		} catch (ServiceException e) {
			return ServiceResponseException.generateResponse(e);
		}	
	}
	
	/**
	 * Update.
	 *
	 * @param payload the payload
	 * @return the string
	 */
	public String update (String payload) {
		try {
			return ClientCall.post(ControllerInterfaceLookup.getURLPath(ControllerInterfaceLookup.driverVehicleMap_UPDATE,  CONTROLLER_IP), payload);
		} catch (ServiceException e) {
			return ServiceResponseException.generateResponse(e);
		}
	}
	public String upsert (String payload) {
		try {
			return ClientCall.post(ControllerInterfaceLookup.getURLPath(ControllerInterfaceLookup.driverVehicleMap_UPSERT,  CONTROLLER_IP), payload);
		} catch (ServiceException e) {
			return ServiceResponseException.generateResponse(e);
		}
	}
	
	/**
	 * Delete.
	 *
	 * @param payload the payload
	 * @return the string
	 */
	public String delete (String payload) {
		try {
			return ClientCall.post(ControllerInterfaceLookup.getURLPath(ControllerInterfaceLookup.driverVehicleMap_DELETE,  CONTROLLER_IP), payload);
		} catch (ServiceException e) {
			return ServiceResponseException.generateResponse(e);
		}
	}
		
	
     public String list (String payload){
    	 try {
 			return ClientCall.post(ControllerInterfaceLookup.getURLPath(ControllerInterfaceLookup.driverVehicleMap_LIST,  CONTROLLER_IP), payload);
 		} catch (ServiceException e) {
 			return ServiceResponseException.generateResponse(e);
 		}
		
	 }
		
	public String get(String vehicleId) {
		try {
			String url = ControllerInterfaceLookup.getURLPath(ControllerInterfaceLookup.driverVehicleMap_GET, CONTROLLER_IP);
			String endpoint = url +"?vehicleId="+vehicleId;
			return ClientCall.get(endpoint);
		} catch (ServiceException e) {
			return ServiceResponseException.generateResponse(e);
		}	
				
	}

	 public String assets (String payload){
    	 try {
 			return ClientCall.post(ControllerInterfaceLookup.getURLPath(ControllerInterfaceLookup.driverVehicleMap_ASSETS,  CONTROLLER_IP), payload);
 		} catch (ServiceException e) {
 			return ServiceResponseException.generateResponse(e);
 		}
		
	 }
}



