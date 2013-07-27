package com.anytraq.notification.service;

import java.util.Date;
import java.util.List;

public interface NotificationDao {

	void save(NotificationEntity notificationEntity);
	
	List<NotificationEntity> listPendingNotifications(Date processingTime);

	List<NotificationEntity> listNotifications(Date processingTime);

	List<NotificationEntity> listRetryNotifications(Date processingTime);

}
