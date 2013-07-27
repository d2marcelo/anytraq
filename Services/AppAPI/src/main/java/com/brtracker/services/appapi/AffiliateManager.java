package com.brtracker.services.appapi;

import com.brtracker.shared.payload.controller.ControllerInterfaceLookup;
import com.brtracker.shared.payload.controller.lookup.ServiceType;
import com.brtracker.shared.utils.wsutils.ClientCall;
import com.brtracker.shared.utils.wsutils.ServiceException;
import com.brtracker.shared.utils.wsutils.ServiceResponseException;

public class AffiliateManager {

	private String CONTROLLER_IP = ServiceLookup.getControllerIP(ServiceType.CONTROLLER);
	

		public String add (String payload) {
		try {
			return ClientCall.post(ControllerInterfaceLookup.getURLPath(ControllerInterfaceLookup.affiliate_ADD,  CONTROLLER_IP), payload);
		} catch (ServiceException e) {
			return ServiceResponseException.generateResponse(e);
		}
		}
		
		public String update (String payload) {
			try {
				return ClientCall.post(ControllerInterfaceLookup.getURLPath(ControllerInterfaceLookup.affiliate_UPDATE,  CONTROLLER_IP), payload);
			} catch (ServiceException e) {
				return ServiceResponseException.generateResponse(e);
			}
		}
		
		public String delete (String payload) {
			try {
				return ClientCall.post(ControllerInterfaceLookup.getURLPath(ControllerInterfaceLookup.affiliate_DELETE,  CONTROLLER_IP), payload);
			} catch (ServiceException e) {
				return ServiceResponseException.generateResponse(e);
			}
		}
		public String list () {
			try {
				return ClientCall.get(ControllerInterfaceLookup.getURLPath(ControllerInterfaceLookup.affiliate_LIST,  CONTROLLER_IP));
			} catch (ServiceException e) {
				return ServiceResponseException.generateResponse(e);
			}
		}

		public String get(String unitAddress) {
			try {
				String url = ControllerInterfaceLookup.getURLPath(ControllerInterfaceLookup.affiliate_GET, CONTROLLER_IP);
				String endpoint = url +"?uniqueKey="+unitAddress;
				System.out.println(endpoint);
				return ClientCall.get(endpoint);
			} catch (ServiceException e) {
				return ServiceResponseException.generateResponse(e);
			}
		}
		
		
}
