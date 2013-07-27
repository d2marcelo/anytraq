package com.brtracker.services.netty.server;

import java.net.SocketAddress;
import java.util.HashMap;
import java.util.Map;

import org.jboss.netty.channel.MessageEvent;

import com.brtracker.shared.utils.logging.MyLogger;

public class DeviceConnectionLookup {

	private MyLogger logger = new MyLogger(DeviceConnectionLookup.class);
	
	private Map<String, MessageEvent> map = new HashMap<String, MessageEvent>();
	
	public void put (String unitAddress, MessageEvent event) {
		SocketAddress remoteAddress = event.getRemoteAddress();
		if (remoteAddress != null) {
			logger.logInfo("DeviceConnectionLookup: putting message event for unit " + 
					unitAddress + " [" + remoteAddress.toString() + "]");;
		} else {
			logger.logInfo("DeviceConnectionLookup: putting message event for unit " + 
					unitAddress + " [CRAP IS NULL]");
		}
		map.put(unitAddress, event);
	}
	
	public MessageEvent getMessageEvent (String unitAddress) {
		
		MessageEvent messageEvent = map.get(unitAddress);
		if (messageEvent != null && messageEvent.getRemoteAddress() != null) {
			logger.logInfo("DeviceConnectionLookup: getting message event for unit " + 
					unitAddress + " [" + messageEvent.getRemoteAddress().toString() + "]");;
		} else {
			logger.logInfo("DeviceConnectionLookup: getting message event for unit " + 
					unitAddress + " [CRAP IS NULL]");
		}
		return map.get(unitAddress);
	}
	
	public String getUnitAddress(SocketAddress remoteAddress) {
		
		if (remoteAddress != null) {
			logger.logInfo("DeviceConnectionLookup: getUnitAddress for address " 
					+ " [" + remoteAddress.toString() + "]");;
		} else {
			logger.logInfo("DeviceConnectionLookup: getUnitAddress for address " 
					+ " [CRAP IS NULL]");
		}

		for (Map.Entry<String, MessageEvent> m : map.entrySet()){
			if (m.getValue().getRemoteAddress().equals(remoteAddress)) {
				logger.logInfo("DeviceConnectionLookup: getUnitAddress found unit " + m.getKey() );
			}
		}

		for (Map.Entry<String, MessageEvent> m : map.entrySet()){
			if (m.getValue().getRemoteAddress().equals(remoteAddress)) {
				return m.getKey();
			}
		}
		return null;
	}

}
