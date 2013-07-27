package com.brtracker.services.appapi;

import com.brtracker.shared.payload.controller.lookup.ServiceType;
import com.brtracker.shared.payload.tracking.TrackingInterfaceLookup;
import com.brtracker.shared.utils.wsutils.ClientCall;
import com.brtracker.shared.utils.wsutils.ServiceException;
import com.brtracker.shared.utils.wsutils.ServiceResponseException;

public class DeviceMessageManager {

	private String TRACKING_IP = ServiceLookup.getTrackingIP(ServiceType.TRACKING);
	
	public String list (String payload) {
		try {
			return ClientCall.post(TrackingInterfaceLookup.getURLPath(TrackingInterfaceLookup.deviceMessage_ADD,  TRACKING_IP), payload);
		} catch (ServiceException e) {
			return ServiceResponseException.generateResponse(e);
		}
	}
}
