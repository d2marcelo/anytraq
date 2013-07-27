package com.brtracker.services.async;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import javassist.tools.rmi.ObjectNotFoundException;
import com.brtracker.services.async.dao.AsyncDao;
import com.brtracker.shared.payload.controller.ConfigurationOverride;
import com.brtracker.shared.payload.controller.DefaultAlertConfiguration;
import com.brtracker.shared.payload.controller.OverrideEntity;
import com.brtracker.shared.payload.controller.data.Account;
import com.brtracker.shared.payload.controller.data.Device;
import com.brtracker.shared.payload.controller.data.Driver;
import com.brtracker.shared.payload.controller.data.DriverVehicleMap;
import com.brtracker.shared.payload.controller.data.Vehicle;
import com.brtracker.shared.payload.controller.lookup.AlertCategoryType;
import com.brtracker.shared.payload.controller.lookup.AlertType;
import com.brtracker.shared.payload.controller.lookup.DeviceState;
import com.brtracker.shared.payload.tracking.AlertMessageRequest;
import com.brtracker.shared.payload.tracking.AlertMessageResponse;
import com.brtracker.shared.payload.tracking.TrackingInterfaceLookup;
import com.brtracker.shared.payload.tracking.data.AlertMessage;
import com.brtracker.shared.payload.tracking.data.AlertReport;
import com.brtracker.shared.utils.ConversionUtils;
import com.brtracker.shared.utils.DateUtil;
import com.brtracker.shared.utils.EventAuditorBuilder;
import com.brtracker.shared.utils.EventType;
import com.brtracker.shared.utils.UnitType;
import com.brtracker.shared.utils.json.JSONMapper;
import com.brtracker.shared.utils.json.JSONMapperException;
import com.brtracker.shared.utils.logging.MyLogger;
import com.brtracker.shared.utils.spring.HibernateHelperException;
import com.brtracker.shared.utils.wsutils.ClientCall;
import com.brtracker.shared.utils.wsutils.ServiceException;

public class UpdateSpeedAlert extends AsyncAction  {
	
	private MyLogger logger = new MyLogger(UpdateSpeedAlert.class, this.getClass().getSimpleName());;
	private AccountWSClient accountWSClient;
	private AsyncDao dao;
	private EventAuditorBuilder eventAuditBuilder;
	

	@Override
	public void run () {
		try {
			logger.logInfo("UpdateSpeedAlert: " + message);
			super.parseMessage();
			if (deviceState.equals(DeviceState.NO_GPS)) return;
			DriverVehicleMap driverVehicleMap = accountWSClient.getDriverVehicleMapFromDevice(address);
			Driver driver = driverVehicleMap.getDriver();
			Vehicle vehicle = driverVehicleMap.getVehicle();
			Device device = accountWSClient.getDevice(address);
			Account account = driver.getAccount();
			// get current coordinates
			Map<String,String> accountAtt = (Map<String, String>) JSONMapper.toObject(account.getAttributes(), Map.class);
			
			Long accountId = driver.getAccount().getId();
			List<DefaultAlertConfiguration> alertConfigList = accountWSClient.getDefaultAlertConfiguration(accountId, AlertCategoryType.DRIVER);
			if (alertConfigList.isEmpty()) {
				logger.logInfo("No alerts found");
				return;
			}
			// get vehicle for this driver (should be memcached)
			float floatSpeed = Float.parseFloat(speed);
			int messageSpeed = Math.round(floatSpeed);
			if (messageSpeed > MAX_SPPED_ALLOWED) {
				logger.logError("Invalid Speed Detected: "+400);
				return;
			}
			int convertedMessageSpeed = messageSpeed;
			String speedUnit = " kph";
			
			for (DefaultAlertConfiguration list : alertConfigList) {
				
				AlertType alertType = list.getAlertType();
				boolean isOverSpeed = alertType.equals(AlertType.DRIVER_OVER_SPEEDING) ? true : false;
				if (!isOverSpeed) continue;
				logger.logInfo("Found speed alert: "+list.getDisplayName());
						
				RegionalSettings regSett = RegionalSettingsHelper.get(account, list.getUser());
				TimeZone timezone = regSett.getTimezone();
				UnitType unitType = regSett.getUnitType();
				Locale locale = regSett.getLocale();
				String accTime = DateUtil.applyTimeZoneOffset(deviceTime,  timezone, "EEE, d MMM yyyy HH:mm:ss Z", locale);
			
				
				Double threshHoldSpeed = Double.parseDouble(list.getValue());
				int convertedThreshHoldSpeed = (int) Math.round(threshHoldSpeed);
				if (UnitType.IMPERIAL.equals(unitType)){
					convertedMessageSpeed = (int) ConversionUtils.kilometersToMiles(messageSpeed);
					convertedThreshHoldSpeed = (int) ConversionUtils.kilometersToMiles(threshHoldSpeed);
					speedUnit = " mph";
				}
				String convertedSpeed = String.valueOf(convertedMessageSpeed) + speedUnit;
				String thresHoldSpeedConversion = String.valueOf(convertedThreshHoldSpeed) + speedUnit;
				
				
				AlertMessageRequest alertMessageRequest = new AlertMessageRequest();
				AlertMessage alertMessage = new AlertMessage();
				alertMessage.setCleared(false);
				alertMessage.setAcknowledged(false);
				alertMessage.setDeleted(false);
				alertMessage.setRead(false);
				alertMessage.setDriverId(driver.getId());
				alertMessage.setVehicleId(vehicle.getId());
				alertMessage.setUserId(list.getUser().getId());
				alertMessage.setAccountId(accountId);
				alertMessage.setAlertTypeId(list.getAlertType().getId());
				alertMessage.setDateCreated(deviceTime);
				alertMessage.setLastUpdated(deviceTime);
				alertMessage.setSystemTime(systemTime);
				List<String> att = new ArrayList<String>();
				att.add(driver.getFirstName() + " " + driver.getLastName());
				att.add(list.getDisplayName());
				att.add(String.valueOf(deviceTime));
				att.add(latitude);
				att.add(longitude);
				att.add(String.valueOf(threshHoldSpeed));
				att.add(String.valueOf(messageSpeed));
				alertMessage.setPlaceHolders(JSONMapper.toString(att));
				alertMessage.setMessageKey(list.getMessageKey());
				alertMessage.setNameKey(list.getNameKey());
				alertMessage.setDisplayName(list.getDisplayName());
				alertMessage.setAlertConfigId(list.getId());
				alertMessage.setShowUi(true);
				alertMessageRequest.setAlertMessage(alertMessage);
				// get all alert messages that are not cleared.
				String trackingUrl = TrackingInterfaceLookup.getURLPath(TrackingInterfaceLookup.alertMessage_LIST, ServiceLookup.getTrackingIP());
				String alertPayload = JSONMapper.toString(alertMessageRequest);
				String alertResponse = ClientCall.post(trackingUrl,alertPayload);

				AlertMessageResponse alertMessageResponse = (AlertMessageResponse) JSONMapper.toObject(alertResponse, AlertMessageResponse.class);
				List<AlertMessage> alertMessageList = alertMessageResponse.getAlertMessageList();

				
				if (hasAsset(list.getOverrides(), vehicle.getId())){
					logger.logInfo("message speed:" +messageSpeed);
					logger.logInfo("threshHoldSpeed:"+ threshHoldSpeed);
					if (messageSpeed > threshHoldSpeed){
						// is clear
						if (alertMessageList.isEmpty()){
							// raise alert. 
							   logger.logInfo("%%%%%%% Raising alert for event overspeed");
							   logger.logInfo("%%%%%%% Driver : " + driver.getFirstName() + " "+ driver.getLastName());
							   logger.logInfo("%%%%%%% Alert Name : " + list.getDisplayName());
							   alertMessage.setCleared(false);
							   alertMessage.setAlertSeverityId(list.getAlertSeverityType().getId());
							   dao.addOrUpdate(alertMessage);
							   sendNotification(list.getNotificationConfiguration(), "over_speed_alert" , driver.getAccount().getLocale() ,driver.getFirstName() + " " + driver.getLastName(),list.getDisplayName(), accTime, thresHoldSpeedConversion, convertedSpeed);
							   
							   // create report 
							   AlertReport alertReport = new AlertReport();
							   alertReport.setAccountId(driver.getAccount().getId());
							   alertReport.setAlertSeverityType(alertMessage.getAlertSeverityId());
							   alertReport.setAlertType(alertMessage.getAlertTypeId());
							   alertReport.setDeviceId(device.getId());
							   alertReport.setLatitude(latitude);
							   alertReport.setLongitude(longitude);
							   alertReport.setThreshold(Double.toString(threshHoldSpeed));
							   alertReport.setViolation(Integer.toString(messageSpeed));
							   alertReport.setDateCreated(deviceTime);
							   alertReport.setLastUpdated(deviceTime);
							   alertReport.setSystemTime(systemTime);
							   
							   dao.addOrUpdate(alertReport);
						}else {
							 logger.logInfo("no alertMessageList.");
						}
					} else {
						// clear speed
						 logger.logInfo("Clearing over speed records.");
						 for (AlertMessage alertM : alertMessageList) {
						   alertM.setCleared(true);
						   dao.addOrUpdate(alertM);
						 }
						
					}
				
							
				}
		} 
		} catch (JSONMapperException e) {
			logger.logError(e.getMessage(), e);
			MqClientFactory.mqClient.sendMessageToQueue(eventAuditBuilder.addEventType(EventType.UPDATE_SPEED_ALERT).addIdType(DEVICE_ADDRESS).addIdValue(null).addInput(message).addFailure(true).addOutput("JSONMapperException: "+ e.getMessage()).toString(),EVENT_AUDITOR_QUEUE);
		} catch (ServiceException e) {
			logger.logError(e.getMessage(), e);
			MqClientFactory.mqClient.sendMessageToQueue(eventAuditBuilder.addEventType(EventType.UPDATE_SPEED_ALERT).addIdType(DEVICE_ADDRESS).addIdValue(null).addInput(message).addFailure(true).addOutput("ServiceException: "+ e.getMessage()).toString(),EVENT_AUDITOR_QUEUE);
		} catch (HibernateHelperException e) {
			logger.logError("Exception while inserting graph data.",e);
			MqClientFactory.mqClient.sendMessageToQueue(eventAuditBuilder.addEventType(EventType.UPDATE_SPEED_ALERT).addIdType(DEVICE_ADDRESS).addIdValue(null).addInput(message).addFailure(true).addOutput("HibernateHelperException: "+ e.getMessage()).toString(),EVENT_AUDITOR_QUEUE);
		} catch (ObjectNotFoundException e) {
			logger.logError("Exception while inserting graph data.",e);
			MqClientFactory.mqClient.sendMessageToQueue(eventAuditBuilder.addEventType(EventType.UPDATE_SPEED_ALERT).addIdType(DEVICE_ADDRESS).addIdValue(null).addInput(message).addFailure(true).addOutput("ObjectNotFoundException: "+ e.getMessage()).toString(),EVENT_AUDITOR_QUEUE);
		} catch (InvalidMessageException e) {
			logger.logError("InvalidMessageException while inserting graph data.",e);
			MqClientFactory.mqClient.sendMessageToQueue(eventAuditBuilder.addEventType(EventType.UPDATE_DRIVER_BEHAVIOR).addIdType(DEVICE_ADDRESS).addIdValue(null).addInput(message).addFailure(true).addOutput("InvalidMessageException: "+ e.getMessage()).toString(),EVENT_AUDITOR_QUEUE);
		} 
	}
	
	
	private boolean hasAsset (List<ConfigurationOverride> overrides, Long id){
		if (overrides.isEmpty()) return true; // if empty, account level. 
		logger.logInfo("Configuration has overrites. looking for asset id: "+ id);
		for (ConfigurationOverride override : overrides){
			boolean isasset = override.getOverrideEntity().equals(OverrideEntity.Vehicle);
			if (isasset){
				logger.logInfo("overrite asset ids: "+override.getEntityIds());
				boolean isAssetOverritten = override.getEntityIds().contains(id);
				if (isAssetOverritten) {
					logger.logInfo("overrite asset found!! raise alert..");
					return true;
				} else {
					logger.logInfo("overrite asset not found. don't raise alert");
				}
			}  else {
				logger.logInfo("overrite type not supported");
			}
		}
		return false;
	}
	
	private void sendNotification (Map<String, Object> map , String templateKey, String locale,  String...args) {
		try {
			for (Map.Entry<String, Object> m : map.entrySet()){
			Map<String, Object> notification = new HashMap<String, Object>();
			List<String> recipients =   (List<String>) m.getValue();
			notification.put("recipients", recipients.toArray());
			notification.put("type", m.getKey());
			notification.put("locale", locale);
			List<String> params = new ArrayList<String>();
			for (String a : args) params.add(a);
			notification.put("scheduledTime",new Long(System.currentTimeMillis()).toString());
			notification.put("parameters", params);
			notification.put("templateKey", templateKey);
			String notificationPayload = JSONMapper.toString(notification);
			logger.logInfo("Sending over speed notification: "+notificationPayload);
			MqClientFactory.mqClient.sendMessageToQueue(notification, NOTIFICATION);
			}
		}  catch (JSONMapperException e) {
			logger.logError(e.getMessage(), e);
		}
	}


	public AccountWSClient getAccountWSClient() {
		return accountWSClient;
	}


	public void setAccountWSClient(AccountWSClient accountWSClient) {
		this.accountWSClient = accountWSClient;
	}


	public AsyncDao getDao() {
		return dao;
	}


	public void setDao(AsyncDao dao) {
		this.dao = dao;
	}


	public EventAuditorBuilder getEventAuditBuilder() {
		return eventAuditBuilder;
	}


	public void setEventAuditBuilder(EventAuditorBuilder eventAuditBuilder) {
		this.eventAuditBuilder = eventAuditBuilder;
	}
	
	
	
}
