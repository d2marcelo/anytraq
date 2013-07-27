package com.anytraq.notification.jms;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.anytraq.notification.service.NotificationService;
import com.brtracker.shared.utils.json.Json;
import com.brtracker.shared.utils.logging.MyLogger;

@Component
public class NotificationListener implements MessageListener {

	private MyLogger logger = new MyLogger(NotificationListener.class);
	
	@Autowired
	private NotificationService notificationService;
	
	@Override
	public void onMessage(Message amqMessage) {
		
		if (amqMessage instanceof TextMessage) {
			
			try {
				TextMessage textMessage = (TextMessage) amqMessage;
				Json payload = Json.read(textMessage.getText());
				
				notificationService.saveNotification(payload);
				
			} catch (Exception e) {
				logger.logError("NotificationListener: an unexpected exception has occurred", e);
			}
		} else {
			logger.logError("NotificationListener an invalid non text message has been provided ... skipping it");
		}
	}

	public void setNotificationService(NotificationService notificationService) {
		this.notificationService = notificationService;
	}

}
