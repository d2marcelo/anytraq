package com.brtracker.services.appapi;
import com.brtracker.shared.payload.controller.lookup.ServiceType;
import com.brtracker.shared.payload.tracking.TrackingInterfaceLookup;
import com.brtracker.shared.utils.wsutils.ClientCall;
import com.brtracker.shared.utils.wsutils.ServiceException;
import com.brtracker.shared.utils.wsutils.ServiceResponseException;

public class AlertMessageManager {

	private String TRACKING_IP = ServiceLookup.getTrackingIP(ServiceType.TRACKING);
	

	public String update (String payload) {
	try {
		return ClientCall.post(TrackingInterfaceLookup.getURLPath(TrackingInterfaceLookup.alertMessage_UPDATE,  TRACKING_IP), payload);
	} catch (ServiceException e) {
		return ServiceResponseException.generateResponse(e);
	}
	}
	
	public String list (String payload) {
		try {
			return ClientCall.post(TrackingInterfaceLookup.getURLPath(TrackingInterfaceLookup.alertMessage_LIST,  TRACKING_IP), payload);
		} catch (ServiceException e) {
			return ServiceResponseException.generateResponse(e);
		}
	}
	
	public String delete (String payload) {
		try {
			return ClientCall.post(TrackingInterfaceLookup.getURLPath(TrackingInterfaceLookup.alertMessage_DELETE,  TRACKING_IP), payload);
		} catch (ServiceException e) {
			return ServiceResponseException.generateResponse(e);
		}
	}

	
}
