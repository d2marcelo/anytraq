package com.anytraq.notification.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.anytraq.notification.service.NotificationService;
import com.anytraq.notification.service.NotificationServiceImpl;
import com.brtracker.shared.utils.logging.MyLogger;

@Component
public class NotificationCronJob implements Job {

	private MyLogger logger = new MyLogger(NotificationServiceImpl.class);
	
	@Autowired
	@Qualifier("notificationService")
	private NotificationService notificationService;
	
	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		
		try {
			
			notificationService.processQueuedNotifications();
			logger.logInfo("Error while processing notification services");
			
		} catch (Exception e) {
			logger.logError("Error while processing notification services", e);
		}
		
	}

	public void setNotificationService(NotificationService notificationService) {
		this.notificationService = notificationService;
	}

}
