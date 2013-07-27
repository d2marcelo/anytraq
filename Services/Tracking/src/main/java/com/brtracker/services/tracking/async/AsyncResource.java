package com.brtracker.services.tracking.async;

import com.brtracker.shared.utils.SystemResourcesUtil;
import com.brtracker.shared.utils.SystemResourcesUtil.ActiveMQ;


public class AsyncResource {

	public static String getUrl (){
		ActiveMQ activeMq = SystemResourcesUtil.ActiveMQ.get();
		return activeMq.getUrl() +":"+ activeMq.getPort();
	}
}
