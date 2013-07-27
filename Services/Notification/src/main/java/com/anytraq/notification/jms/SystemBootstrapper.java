package com.anytraq.notification.jms;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.brtracker.shared.utils.SystemConfiguration;
import com.brtracker.shared.utils.activemq.JmsSender;
import com.brtracker.shared.utils.logging.MyLogger;

@Component
public class SystemBootstrapper implements InitializingBean {

	private MyLogger logger = new MyLogger(SystemBootstrapper.class);

	private String queueName = SystemConfiguration.NOTIFICATION;
	
	@Autowired
	private JmsSender jmsSender;
	
	@Autowired
	private NotificationListener notificationListener;
	
	public void start() throws Exception {

		try {
			logger.logInfo("Registering the NotificationListener on " + queueName);
			jmsSender.registerQueueConsumer(queueName, notificationListener);
			logger.logInfo("NotificationListener successfully registered");
		} catch (Exception e) {
			logger.logError("Unexpected exception during messaging bootstrap", e);
		}

	}

//	public void setNotificationListener(NotificationListener notificationListener) {
//		this.notificationListener = notificationListener;
//	}

	@Override
	public void afterPropertiesSet() throws Exception {
		start();
	}

}
