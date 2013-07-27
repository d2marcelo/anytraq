package com.brtracker.services.appapi;

import com.brtracker.shared.payload.controller.lookup.ServiceType;
import com.brtracker.shared.payload.tracking.TrackingInterfaceLookup;
import com.brtracker.shared.utils.wsutils.ClientCall;
import com.brtracker.shared.utils.wsutils.ServiceException;
import com.brtracker.shared.utils.wsutils.ServiceResponseException;

public class DriverBehaviorManager {

private String TRACKING_IP = ServiceLookup.getTrackingIP(ServiceType.TRACKING);
	
	public String list (Long driverId, int duration) {
		try {
			String url=  TrackingInterfaceLookup.getURLPath(TrackingInterfaceLookup.driverBehavior_LIST,  TRACKING_IP);
			String endpoint = url +"?driverId="+driverId+"&duration="+duration;
			return ClientCall.get(endpoint); 
		} catch (ServiceException e) {
			return ServiceResponseException.generateResponse(e);
		}
	}
}
