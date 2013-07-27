package com.brtracker.services.async;

import com.brtracker.shared.utils.SystemResourcesUtil;
import com.brtracker.shared.utils.SystemResourcesUtil.Account;
import com.brtracker.shared.utils.SystemResourcesUtil.Netty;
import com.brtracker.shared.utils.SystemResourcesUtil.Tracking;
import com.brtracker.shared.utils.SystemResourcesUtil.UIAlert;

public class ServiceLookup {

	private static String trackingUrl;
	private static String controlelrUrl;
	private static String uiAlert;
	private static String nettyUrl;
	
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
	
	private static void loadUIUrl () {
		if (uiAlert == null){
			UIAlert uialert = SystemResourcesUtil.UIAlert.get();
			uiAlert = uialert.getUrl();
		} 
		
	}
	private static void loadNetty(){
		if (nettyUrl == null){
			Netty netty = SystemResourcesUtil.Netty.get();
			nettyUrl = netty.getUrl() +":"+netty.getPort();
		} 
	}
	public static String getTrackingIP (){
		loadTracking ();
		return trackingUrl;
	}
	
	public static String getUIAlert (){
		loadUIUrl ();
		return uiAlert;
	}
	
	public static String getControllerIP (){
		loadController ();
		return controlelrUrl;
	}
	
	public static String getNettyIP(){
		loadNetty();
		return nettyUrl;
	}
}
