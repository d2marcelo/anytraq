package com.anytraq.notification.connector;

import java.util.Map;

import com.anytraq.notification.service.NotificationEntity;
import com.anytraq.notification.service.NotificationServiceException;
import com.anytraq.notification.service.NotificationTemplateEntity;

public class NotificationConnectorManager implements NotificationConnector {

	private Map<String, NotificationConnector> connectorsMap;
	
	@Override
	public void sendNotification(NotificationEntity n, String formattedMessage, NotificationTemplateEntity template) {
		
		Object type = n.getType();
		
		NotificationConnector connector = connectorsMap.get(type);
		
		if (connector == null) {
			throw new NotificationServiceException("No available notification connector for type " + type);
		}
		
		connector.sendNotification(n, formattedMessage, template);
	}

	public void setConnectorsMap(Map<String, NotificationConnector> connectorsMap) {
		this.connectorsMap = connectorsMap;
	}

}
