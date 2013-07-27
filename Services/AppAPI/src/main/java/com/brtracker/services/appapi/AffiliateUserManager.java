package com.brtracker.services.appapi;

import com.brtracker.shared.payload.controller.ControllerInterfaceLookup;
import com.brtracker.shared.payload.controller.lookup.ServiceType;
import com.brtracker.shared.utils.wsutils.ClientCall;
import com.brtracker.shared.utils.wsutils.ServiceException;
import com.brtracker.shared.utils.wsutils.ServiceResponseException;

public class AffiliateUserManager {

	private String CONTROLLER_IP = ServiceLookup.getControllerIP(ServiceType.CONTROLLER);
	

		public String add (String payload) {
		try {
			return ClientCall.post(ControllerInterfaceLookup.getURLPath(ControllerInterfaceLookup.affiliateUser_ADD,  CONTROLLER_IP), payload);
		} catch (ServiceException e) {
			return ServiceResponseException.generateResponse(e);
		}
		}
		
		public String login (String payload) {
			try {
				return ClientCall.post(ControllerInterfaceLookup.getURLPath(ControllerInterfaceLookup.affiliateUser_LOGIN, CONTROLLER_IP), payload);
			} catch (ServiceException e) {
				return ServiceResponseException.generateResponse(e);
			}
		}
		public String update (String payload) {
			try {
				return ClientCall.post(ControllerInterfaceLookup.getURLPath(ControllerInterfaceLookup.affiliateUser_UPDATE,  CONTROLLER_IP), payload);
			} catch (ServiceException e) {
				return ServiceResponseException.generateResponse(e);
			}
		}
		
		public String delete (String payload) {
			try {
				return ClientCall.post(ControllerInterfaceLookup.getURLPath(ControllerInterfaceLookup.affiliateUser_DELETE,  CONTROLLER_IP), payload);
			} catch (ServiceException e) {
				return ServiceResponseException.generateResponse(e);
			}
		}
		public String list (String payload) {
			try {
				return ClientCall.post(ControllerInterfaceLookup.getURLPath(ControllerInterfaceLookup.affiliateUser_LIST,  CONTROLLER_IP), payload);
			} catch (ServiceException e) {
				return ServiceResponseException.generateResponse(e);
			}
		}
		
		
}
