package com.anytraq.notification.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.brtracker.shared.utils.spring.HibernateHelper;
import com.brtracker.shared.utils.spring.HibernateHelperException;

public class NotificationDaoImpl extends HibernateHelper implements NotificationDao {

	@Override
	public void save(NotificationEntity notification) {
		try {
			super.saveOrUpdate(notification);
		} catch (HibernateHelperException e) {
			throw new NotificationServiceException("Unexpected exception while saving notification ", e);
		}
	}

	@Override
	public List<NotificationEntity> listPendingNotifications(Date processingTime) {
		
		List<NotificationEntity> notifications = super.find(
				"fetch.pending.notifications", 
				new String[] {"processingTime"}, 
				new Object[] {processingTime});
		
		return notifications;
	}
	
	@Override
	public List<NotificationEntity> listRetryNotifications(Date processingTime) {
		
		List<NotificationEntity> notifications = super.find(
				"fetch.retry.notifications", 
				new String[] {"processingTime"}, 
				new Object[] {processingTime});
		
		return notifications;
	}

	@Override
	public List<NotificationEntity> listNotifications(Date processingTime) {
		
		List<NotificationEntity> notifications = new ArrayList<NotificationEntity>(1000); 

		notifications.addAll(listPendingNotifications(processingTime));
		
		notifications.addAll(listRetryNotifications(processingTime));
		return notifications;
	}

}
