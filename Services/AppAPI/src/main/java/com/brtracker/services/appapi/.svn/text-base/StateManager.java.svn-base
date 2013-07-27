package com.brtracker.services.appapi;

import com.brtracker.shared.payload.controller.ControllerInterfaceLookup;
import com.brtracker.shared.payload.controller.lookup.ServiceType;
import com.brtracker.shared.utils.wsutils.ClientCall;
import com.brtracker.shared.utils.wsutils.ServiceException;
import com.brtracker.shared.utils.wsutils.ServiceResponseException;

public class StateManager {

	private String CONTROLLER_IP = ServiceLookup.getControllerIP(ServiceType.CONTROLLER);
	

	public String add (String payload) {
	try {
		return ClientCall.post(ControllerInterfaceLookup.getURLPath(ControllerInterfaceLookup.state_ADD,  CONTROLLER_IP), payload);
	} catch (ServiceException e) {
		return ServiceResponseException.generateResponse(e);
	}
	}
	
	public String delete (String payload) {
		try {
			return ClientCall.post(ControllerInterfaceLookup.getURLPath(ControllerInterfaceLookup.state_DELETE,  CONTROLLER_IP), payload);
		} catch (ServiceException e) {
			return ServiceResponseException.generateResponse(e);
		}
		}
	
	
	public String list (Long stateId) {
		try {
			String url = ControllerInterfaceLookup.getURLPath(ControllerInterfaceLookup.state_LIST, CONTROLLER_IP);
			String endpoint = url +"?countryId="+stateId;
			return ClientCall.get(endpoint);
		} catch (ServiceException e) {
			return ServiceResponseException.generateResponse(e);
		}
		}
}
