package com.brtracker.services.appapi;

import com.brtracker.shared.payload.controller.lookup.ServiceType;
import com.brtracker.shared.utils.SystemResourcesUtil;
import com.brtracker.shared.utils.SystemResourcesUtil.Account;
import com.brtracker.shared.utils.SystemResourcesUtil.Tracking;

public class ServiceLookup {

	private static String trackingUrl;
	private static String controlelrUrl;
	
	private static void loadTracking () {
		if (trackingUrl == null){
			Tracking tracking = SystemResourcesUtil.Tracking.get();
			trackingUrl = tracking.getUrl() +":"+tracking.getPort();
		} 
		
	}
	
	private static void loadController () {
		if (controlelrUrl == null){
			Account controller = SystemResourcesUtil.Account.get();
			controlelrUrl = controller.getUrl() +":"+controller.getPort();
		} 
		
	}
	
	public static String getTrackingIP (ServiceType service){
		loadTracking ();
		return trackingUrl;
	}
	
	public static String getControllerIP (ServiceType service){
		loadController ();
		return controlelrUrl;
	}
}
