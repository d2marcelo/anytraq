package com.anytraq.notification.service;

import static com.brtracker.shared.payload.notification.NotificationMessageConstants.PARAMETERS;

import java.text.MessageFormat;
import java.util.List;

import org.springframework.stereotype.Component;

import com.brtracker.shared.utils.json.Json;

@Component
public class NotificationTemplateHelper {
	
	public String formatNotificationMessage(NotificationTemplateEntity template, 
			NotificationEntity notification) {
		
		String finalMessage = template.getTemplate();
		
		String notificationPayload = notification.getPayload();
		Json notificationJson = Json.read(notificationPayload);

		if (notificationJson.has(PARAMETERS)) {
			List<Object> parameters = notificationJson
				.at(PARAMETERS).asList();
			
			String formatterMessage = MessageFormat.format(
					finalMessage, parameters.toArray());
			
			finalMessage = formatterMessage;
		}
		
		return finalMessage;
	}
}
