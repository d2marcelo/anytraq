package com.brtracker.services.midlink.processing;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import com.brtracker.shared.utils.logging.MyLogger;
import com.brtracker.shared.utils.packet.DefaultTrackingMessage;
import com.brtracker.shared.utils.spring.HibernateHelperException;

public class StaleDeviceMonitor {
	
	private MyLogger logger = new MyLogger(StaleDeviceMonitor.class);
	
	private DeviceStateDao deviceStateDao;
	private Map<String, DeviceStateAwareMessageAction> actions;
	
	public void monitorStaleDevices() {
		try {
			Date now = GregorianCalendar.getInstance().getTime();
			List<DeviceStateEntity> staleDevices = deviceStateDao.getStaleDevices(now);
			
			for (DeviceStateEntity e: staleDevices) {
				
				DefaultTrackingMessage trackingMessage = new DefaultTrackingMessage();
				trackingMessage.deserialize(e.getJsonDetails());
				
				String messageType = trackingMessage.getMessageType();
				
				DeviceStateAwareMessageAction action = actions.get(messageType);
				
				if (action != null) {
					try {
						
						action.execute(trackingMessage, e);
						
					} catch (Exception ex) {
						logger.logError("Unexpected exception while action " + 
								action + " execution on message " + trackingMessage, ex);
					}
				} else {
					logger.logInfo("Message without supporting Action, this is weird !!!");
				}
			}
			
		} catch (HibernateHelperException e) {
		}
	}

	public void setActions(Map<String, DeviceStateAwareMessageAction> actions) {
		this.actions = actions;
	}

	public void setDeviceStateDao(DeviceStateDao deviceStateDao) {
		this.deviceStateDao = deviceStateDao;
	}
	
}
