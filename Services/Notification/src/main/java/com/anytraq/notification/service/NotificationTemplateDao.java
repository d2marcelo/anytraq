package com.anytraq.notification.service;

public interface NotificationTemplateDao {
	void save(NotificationTemplateEntity notificationTemplateEntity);
	
	NotificationTemplateEntity getNotificationTemplateByKey(
			String templateKey, String locale, String type);
	
}
