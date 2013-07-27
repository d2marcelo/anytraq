package com.anytraq.notification.job;

import static com.brtracker.shared.utils.SystemConfiguration.*;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.anytraq.notification.service.NotificationServiceException;
import com.brtracker.shared.utils.SystemConfiguration;
import com.brtracker.shared.utils.logging.MyLogger;

@Component
public class NotificationJobStarter implements InitializingBean, DisposableBean {

	private MyLogger logger = new MyLogger(NotificationJobStarter.class);
	
	@Autowired
	private SystemConfiguration systemConfiguration;
	
	@Autowired
	@Qualifier("notificationJobDetail")
	private JobDetail notificationJobDetail;
	
	// by default 30 seconds
	private static final String DEFAULT_CRON_EXP = "0/30 * * * * ?";
	
	private Scheduler sched;
	
	public void start() {
		
		try {
			
			logger.logInfo("NotificationJobStarter ... starting scheduler ");
			
			String cronExpression = systemConfiguration.getConfigElement(NOTIFICATION, NOTIFICATION_CRON_EXP);
			
			if (cronExpression == null) {
				cronExpression = DEFAULT_CRON_EXP;
			}
			
			SchedulerFactory sf = new StdSchedulerFactory();
			sched = sf.getScheduler();
				
			CronTrigger trigger = new CronTrigger("notification_trigger", "notification_group", 
					"notification_job", "notification_group", cronExpression);
			
			sched.scheduleJob(notificationJobDetail, trigger);
			
	        sched.start();
	        
		} catch (Exception e) {
			throw new NotificationServiceException("NotificationJobStarter failed to start the notification job", e);
		}
		
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		start();
	}

	@Override
	public void destroy() throws Exception {
		shutdown();
	}

	private void shutdown() {
		try {
			if (sched != null) {
				logger.logInfo("NotificationJobStarter ... shutting down scheduler ");
				sched.shutdown();
			}
		} catch (Exception e) {
			
		}
	}

}
