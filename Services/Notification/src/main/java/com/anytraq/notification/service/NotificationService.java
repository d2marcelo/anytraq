package com.anytraq.notification.service;

import com.brtracker.shared.utils.json.Json;

public interface NotificationService {

	void saveNotification(Json payload);
	
	NotificationTemplateEntity getNotificationTemplateByKey(
			String templateKey, String locale, String type);
	
	void processQueuedNotifications();

}
