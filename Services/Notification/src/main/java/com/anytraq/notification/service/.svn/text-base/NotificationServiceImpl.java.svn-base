package com.anytraq.notification.service;

import static com.brtracker.shared.payload.notification.NotificationMessageConstants.*;
import static com.brtracker.shared.utils.SystemConfiguration.*;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.anytraq.notification.connector.NotificationConnector;
import com.anytraq.notification.service.NotificationServiceException.Reason;
import com.brtracker.shared.utils.StringUtil;
import com.brtracker.shared.utils.SystemConfiguration;
import com.brtracker.shared.utils.json.Json;
import com.brtracker.shared.utils.json.JsonUtils;
import com.brtracker.shared.utils.logging.MyLogger;

@Service("notificationService")
public class NotificationServiceImpl implements NotificationService {

	private MyLogger logger = new MyLogger(NotificationServiceImpl.class);
	
	@Autowired
	private NotificationDao notificationDao;
	
	@Autowired
	private NotificationTemplateDao notificationTemplateDao;
	
	@Autowired
	private NotificationTemplateHelper templateHelper;
	
	@Autowired
	@Qualifier("systemConfiguration")
	private SystemConfiguration systemConfiguration;

	@Autowired
	@Qualifier("connectorManager")
	private NotificationConnector connectorManager;
	
	@Autowired
	private RecipientInfoDao recipientInfoDao;
	
	private static final Map<String, String> internationalCodes;
	
	static {
		internationalCodes = new HashMap<String, String>();
		internationalCodes.put("pt_br", "+55");
	}
	
	private Map<String, NotificationTemplateEntity> templateCache = 
		new HashMap<String, NotificationTemplateEntity>(); 
	
	@Override
	public void saveNotification(Json payload) {

		try {
			abortIfInvalid(payload);
			
			Calendar scheduledTime = GregorianCalendar.getInstance();
			String scheduleForValue = JsonUtils.asSafeString(payload, SCHEDULED_TIME);
			if (!StringUtils.isEmpty(scheduleForValue)) {
				scheduledTime.setTimeInMillis(Long.valueOf(scheduleForValue));
			}
			
			String locale = "en_US";
			String localeValue = JsonUtils.asSafeString(payload, LOCALE);
			if (!StringUtils.isEmpty(localeValue)) {
				locale = localeValue;
			}

			String templateKey = payload.at(TEMPLATE_KEY).asString();
			String type = payload.at(TYPE).asString();
			String fullPayload = payload.toString();
			
			List<Object> parameters = payload.at(RECIPIENTS).asList();
			for (Object recipient : parameters) {
				
				String recipientAddress = recipient.toString();
				if ("sms".equals(type)) {
					recipientAddress = StringUtil.sanitizePhoneNumber(recipientAddress);
					recipientAddress = getInternationalNumber(recipientAddress, locale);
				}
				
				String state = NotificationEntity.PENDING_STATE;
//				if (recipientExceededQuota(recipientAddress)) {
//					state = NotificationEntity.EXCEEDED_QUOTA_STATE;
//				}
				
				saveNotificationEntity(templateKey, type, 
						fullPayload, recipientAddress , scheduledTime, locale, state);
			}
			
			
		} catch (NotificationServiceException e) {
			logger.logError("Notification exception while saving message", e);
			throw e;
		} catch (Exception e) {
			logger.logError("Unexpected exception while saving message", e);
		}
		
	}
	
	private boolean recipientExceededQuota(String recipientAddress) {
		
		boolean quotaExceeded = false;
		
		// Default quota configuration 15 messages per 30 days
		int quota = Integer.valueOf(systemConfiguration
				.getConfigElement(NOTIFICATION, NOTIFICATION_QUOTA, "15"));

		int quotaPeriod = Integer.valueOf(systemConfiguration
				.getConfigElement(NOTIFICATION, NOTIFICATION_QUOTA_PERIOD, "30"));

		RecipientInfoEntity recipient = recipientInfoDao.getRecipientByAddress(recipientAddress);

		Calendar nowCal = GregorianCalendar.getInstance();
		
		if (recipient != null) {
			
			int totalMessagesSent = recipient.getTotalMessagesSent();
			Date currentPeriodStartDate = recipient.getCurrentPeriodStartDate();
			int currentMessagesSent = recipient.getCurrentMessagesSent();
			
			Calendar lastActivityCal = GregorianCalendar.getInstance();
			lastActivityCal.setTime(currentPeriodStartDate);
			
			int currentPeriod = nowCal.get(Calendar.DAY_OF_YEAR) - lastActivityCal.get(Calendar.DAY_OF_YEAR);
			
			if ( currentPeriod < quotaPeriod ) {
				if (currentMessagesSent >= quota) {
					quotaExceeded = true;
				} else {
					recipient.setTotalMessagesSent(totalMessagesSent+1);
					recipient.setCurrentMessagesSent(currentMessagesSent+1);
				}
			} else {
				recipient.setCurrentMessagesSent(0);
				recipient.setCurrentPeriodStartDate(nowCal.getTime());
			}
			
		} else {
			recipient = new RecipientInfoEntity();
			recipient.setRecipientAddress(recipientAddress);
			recipient.setTotalMessagesSent(1);
			recipient.setCurrentMessagesSent(1);
			recipient.setCurrentPeriodStartDate(nowCal.getTime());
		}
		recipientInfoDao.save(recipient);
		return quotaExceeded;
	}
	
	

	private String getInternationalNumber(String recipientAddress, String locale) {
		String internationalNumber = StringUtils.remove(recipientAddress, '-');
		String internationalCode = internationalCodes.get(locale.toLowerCase());
		if (internationalCode != null) {
			if (!internationalNumber.startsWith(internationalCode)) {
				internationalNumber = internationalCode + recipientAddress;
				logger.logInfo("Adjusted for international sms original/updated" + recipientAddress + "/" + internationalNumber);
			}
		}
		return internationalNumber;
	}

	private void saveNotificationEntity(String templateKey, String type, String payload, 
			String recipient, Calendar scheduledTime, String locale, String state) {
		
		logger.logInfo("Saving notification " + type + "/" + recipient);
		
		NotificationEntity notificationEntity = new NotificationEntity();
		notificationEntity.setScheduledTime(scheduledTime.getTime());
		notificationEntity.setLocale(locale);
		notificationEntity.setTemplateKey(templateKey);
		notificationEntity.setType(type);
		notificationEntity.setPayload(payload.toString());
		notificationEntity.setRecipient(recipient);
		notificationEntity.setState(state);
		
		notificationDao.save(notificationEntity);
	}
	
	@Override
	public NotificationTemplateEntity getNotificationTemplateByKey(
			String templateKey, String locale, String type) {
		
		return notificationTemplateDao.getNotificationTemplateByKey(
				templateKey, locale, type);
	}

	@Override
	public void processQueuedNotifications() {
		
		templateCache.clear();
		
		Calendar cal = GregorianCalendar.getInstance();
		
		List<NotificationEntity> notifications = 
			notificationDao.listNotifications(cal.getTime());
		
		for (NotificationEntity n : notifications) {
			
			NotificationTemplateEntity template = findTemplate(n);
			
			if (template != null) {
				
				try {
					
					String formattedMessage = templateHelper.formatNotificationMessage(template, n);
					
					logger.logInfo("Formated message for notification [" + formattedMessage + "]");
					connectorManager.sendNotification(n, formattedMessage, template);
					
					n.setState(NotificationEntity.SUCCESS_STATE);
					
					notificationDao.save(n);
					
				} catch (Exception e) {
					handleNotificationFailure(n, e);
				}
			}
		
		}
	}

	private void handleNotificationFailure(NotificationEntity n, Exception e) {

		try {
			
			logger.logError("Processing notification failure for " + n.getId(), e);
			int currentRetries = n.getRetries();
			
			if (shouldRetry(n, e)) {

				// giving it another try 
				n.setState(NotificationEntity.RETRY_STATE);
				
				Calendar cal = GregorianCalendar.getInstance();
				cal.setTime(n.getScheduledTime());
				int retryAfterMins = Integer.valueOf(systemConfiguration
						.getConfigElement(NOTIFICATION, NOTIFICATION_RETRY_AFTER_MINS, "30"));
				// we will retry it after retryAfterMins minutes
				cal.set(Calendar.MINUTE, retryAfterMins);
				n.setScheduledTime(cal.getTime());
				
				n.setRetries(currentRetries+1);
				
			} else {

				// just failing the notification
				n.setState(NotificationEntity.FAILED_STATE);

			}
			notificationDao.save(n);

		} catch (Exception ee) {
			logger.logError("Error while handleNotificationFailure... just failing the notification " + n.getId(), e);

			// just failing the notification
			n.setState(NotificationEntity.FAILED_STATE);
			notificationDao.save(n);
		}
		
	}

	private boolean shouldRetry(NotificationEntity n, Exception e) {
		boolean retry = true;
		
		if (e instanceof NotificationServiceException) {

			NotificationServiceException ne = (NotificationServiceException) e;
			if (ne.getReason().equals(Reason.INVALID_RECIPIENT)) {
				retry = false;
			}
			
		} 
		if (retry) {

			int maxRetries = Integer.valueOf(systemConfiguration
					.getConfigElement(NOTIFICATION, NOTIFICATION_MAX_RETRIES, "3"));
			
			int currentRetries = n.getRetries();
			
			if (currentRetries  >= maxRetries) {
				retry = false;
			}

		}
		return retry;
	}

	private NotificationTemplateEntity findTemplate(NotificationEntity n) {
		
		String templateKey = n.getTemplateKey();
		String locale = n.getLocale();
		String type = n.getType();
		String k = n.buildUniqueKey();
		
		NotificationTemplateEntity template = templateCache.get(k);
		
		if (template == null) {
			template = notificationTemplateDao
				.getNotificationTemplateByKey(templateKey, locale, type);
			
			if (template == null) {
				logger.logError("The notification " + n.getId() + " refers to an invalid template " + n.buildUniqueKey());
			} else {
				templateCache.put(k, template);
			}
		}
		return template;
	}
	
	private void abortIfInvalid(Json payload) {

		abortIfMissingProperty(payload, TEMPLATE_KEY);
		
		abortIfMissingProperty(payload, TYPE);
		
		abortIfMissingArrayProperty(payload, RECIPIENTS);
	}
	
	private void abortIfMissingProperty(Json payload, String property) {
		if (!payload.has(property) || StringUtils.isEmpty(payload.at(property).asString())) {
			throw new NotificationServiceException("Missing " + property + " in message payload \n" + payload.toString());
		}
	}

	private void abortIfMissingArrayProperty(Json payload, String property) {
		boolean propertyExists = payload.has(property);
		List<Json> l = payload.at(property).asJsonList();
		
		if (!propertyExists || l == null || l.size() == 0 ) {
			throw new NotificationServiceException("Missing array " + property + " in message payload \n" + payload.toString());
		}
	}

}
