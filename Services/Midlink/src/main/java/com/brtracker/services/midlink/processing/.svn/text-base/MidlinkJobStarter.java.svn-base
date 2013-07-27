package com.brtracker.services.midlink.processing;

import static com.brtracker.shared.utils.SystemConfiguration.*;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import com.brtracker.shared.utils.SystemConfiguration;
import com.brtracker.shared.utils.logging.MyLogger;

public class MidlinkJobStarter implements InitializingBean, DisposableBean {

	private MyLogger logger = new MyLogger(MidlinkJobStarter.class);
	
	private SystemConfiguration systemConfiguration;
	private JobDetail midlinkJobDetail;
	private JobDetail commandRetryJobDetail;
	
	// each 7 minutes by default
	private static final String DEFAULT_CRON_EXP = "0 0/7 * * * ?";
	private Scheduler sched;
	
	public void start() {
		
		try {
			String cronExpression = systemConfiguration
				.getConfigElement(MIDLINK, MIDLINK_CRON_EXP, DEFAULT_CRON_EXP);
			
			SchedulerFactory sf = new StdSchedulerFactory();
			sched = sf.getScheduler();
				
//			CronTrigger staleDeviceTrigger = new CronTrigger("stale_device_monitor_trigger", "stale_device_monitor_group", 
//					"stale_device_monitor_job", "stale_device_monitor_group", cronExpression);

			CronTrigger commandRetryTrigger = new CronTrigger("command_retry_monitor_trigger", "command_retry_monitor_group", 
					"command_retry_monitor_job", "command_retry_monitor_group", cronExpression);

//			sched.scheduleJob(midlinkJobDetail, staleDeviceTrigger);
			sched.scheduleJob(commandRetryJobDetail, commandRetryTrigger);
			
	        sched.start();
	        
		} catch (Exception e) {
			throw new RuntimeException("NotificationJobStarter failed to start the notification job", e);
		}
		
	}

	private void shutdown() {
		try {
			if (sched != null) {
				logger.logInfo("MidlinkJobStarter ... shutting down scheduler ");
				sched.shutdown();
			}
		} catch (Exception e) {
			
		}
	}

	public void setSystemConfiguration(SystemConfiguration systemConfiguration) {
		this.systemConfiguration = systemConfiguration;
	}

	public void setMidlinkJobDetail(JobDetail midlinkJobDetail) {
		this.midlinkJobDetail = midlinkJobDetail;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		start();
	}

	@Override
	public void destroy() throws Exception {
		shutdown();
	}

	public void setCommandRetryJobDetail(JobDetail commandRetryJobDetail) {
		this.commandRetryJobDetail = commandRetryJobDetail;
	}

}
