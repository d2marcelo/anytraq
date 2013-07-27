package com.brtracker.services.appapi;

import com.brtracker.shared.payload.controller.ControllerInterfaceLookup;
import com.brtracker.shared.payload.controller.lookup.ServiceType;
import com.brtracker.shared.payload.tracking.TrackingInterfaceLookup;
import com.brtracker.shared.utils.wsutils.ClientCall;
import com.brtracker.shared.utils.wsutils.ServiceException;
import com.brtracker.shared.utils.wsutils.ServiceResponseException;

public class DeviceManager {

	private String CONTROLLER_IP = ServiceLookup.getControllerIP(ServiceType.CONTROLLER);
	private String TRACKING_IP = ServiceLookup.getTrackingIP(ServiceType.TRACKING);

		public String add (String payload) {
		try {
			return ClientCall.post(ControllerInterfaceLookup.getURLPath(ControllerInterfaceLookup.device_ADD,  CONTROLLER_IP), payload);
		} catch (ServiceException e) {
			return ServiceResponseException.generateResponse(e);
		}
		}
		
		public String update (String payload) {
			try {
				return ClientCall.post(ControllerInterfaceLookup.getURLPath(ControllerInterfaceLookup.device_UPDATE,  CONTROLLER_IP), payload);
			} catch (ServiceException e) {
				return ServiceResponseException.generateResponse(e);
			}
		}
		
		public String delete (String payload) {
			try {
				return ClientCall.post(ControllerInterfaceLookup.getURLPath(ControllerInterfaceLookup.device_DELETE,  CONTROLLER_IP), payload);
			} catch (ServiceException e) {
				return ServiceResponseException.generateResponse(e);
			}
		}
		public String list (String payload) {
			try {
				return ClientCall.post(ControllerInterfaceLookup.getURLPath(ControllerInterfaceLookup.device_LIST,  CONTROLLER_IP), payload);
			} catch (ServiceException e) {
				return ServiceResponseException.generateResponse(e);
			}
		}

		public String get(String unitAddress) {
			try {
				String url = ControllerInterfaceLookup.getURLPath(ControllerInterfaceLookup.device_GET, CONTROLLER_IP);
				String endpoint = url +"?unitAddress="+unitAddress;
				return ClientCall.get(endpoint);
			} catch (ServiceException e) {
				return ServiceResponseException.generateResponse(e);
			}
		}

		public String history(Long deviceId, String date) {
			try {
				String url = TrackingInterfaceLookup.getURLPath(TrackingInterfaceLookup.deviceHistory_GET, TRACKING_IP);
				String endpoint = url +"?deviceId="+deviceId +"&date="+date;
				return ClientCall.get(endpoint);
			} catch (ServiceException e) {
				return ServiceResponseException.generateResponse(e);
			}
		}

		public String historyDates(Long deviceId) {
			try {
				String url = TrackingInterfaceLookup.getURLPath(TrackingInterfaceLookup.deviceHistory_DATES, TRACKING_IP);
				String endpoint = url +"?deviceId="+deviceId;
				return ClientCall.get(endpoint);
			} catch (ServiceException e) {
				return ServiceResponseException.generateResponse(e);
			}
		}
		
		
}
