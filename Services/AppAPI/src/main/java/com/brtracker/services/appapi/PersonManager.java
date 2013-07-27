package com.brtracker.services.appapi;

import com.brtracker.shared.payload.controller.ControllerInterfaceLookup;
import com.brtracker.shared.payload.controller.lookup.ServiceType;
import com.brtracker.shared.utils.wsutils.ClientCall;
import com.brtracker.shared.utils.wsutils.ServiceException;
import com.brtracker.shared.utils.wsutils.ServiceResponseException;

public class PersonManager {

	private String CONTROLLER_IP = ServiceLookup.getControllerIP(ServiceType.CONTROLLER);
	

		public String add (String payload) {
		try {
			return ClientCall.post(ControllerInterfaceLookup.getURLPath(ControllerInterfaceLookup.driver_ADD,  CONTROLLER_IP), payload);
		} catch (ServiceException e) {
			return ServiceResponseException.generateResponse(e);
		}
		}
		
		public String update (String payload) {
			try {
				return ClientCall.post(ControllerInterfaceLookup.getURLPath(ControllerInterfaceLookup.driver_UPDATE,  CONTROLLER_IP), payload);
			} catch (ServiceException e) {
				return ServiceResponseException.generateResponse(e);
			}
		}
		
		public String delete (String payload) {
			try {
				return ClientCall.post(ControllerInterfaceLookup.getURLPath(ControllerInterfaceLookup.driver_DELETE,  CONTROLLER_IP), payload);
			} catch (ServiceException e) {
				return ServiceResponseException.generateResponse(e);
			}
		}
		public String list (String payload) {
			try {
				return ClientCall.post(ControllerInterfaceLookup.getURLPath(ControllerInterfaceLookup.driver_LIST,  CONTROLLER_IP), payload);
			} catch (ServiceException e) {
				return ServiceResponseException.generateResponse(e);
			}
		}
		
		public String search (String payload) {
			try {
				return ClientCall.post(ControllerInterfaceLookup.getURLPath(ControllerInterfaceLookup.driver_SEARCH,  CONTROLLER_IP), payload);
			} catch (ServiceException e) {
				return ServiceResponseException.generateResponse(e);
			}
		}
		
		public String get(String socialSecurity) {
			try {
				String url = ControllerInterfaceLookup.getURLPath(ControllerInterfaceLookup.driver_GET, CONTROLLER_IP);
				String endpoint = url +"?socialSecurity="+socialSecurity;
				return ClientCall.get(endpoint);
			} catch (ServiceException e) {
				return ServiceResponseException.generateResponse(e);
			}
		}
		
}
