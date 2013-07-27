package com.brtracker.services.tracking;

import com.brtracker.shared.payload.controller.lookup.ServiceType;
import com.brtracker.shared.utils.SystemResourcesUtil;
import com.brtracker.shared.utils.SystemResourcesUtil.Account;

public class ServiceLookup {

	private static String controlelrUrl;
	
	public static String getServiceIP (ServiceType service){
		loadController();
		return controlelrUrl;
	}
	
	private static void loadController () {
		if (controlelrUrl == null){
			Account controller = SystemResourcesUtil.Account.get();
			controlelrUrl = controller.getUrl() +":"+controller.getPort();
		} 
		
	}
}
