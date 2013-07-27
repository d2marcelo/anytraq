package com.brtracker.services.async;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import javassist.tools.rmi.ObjectNotFoundException;
import com.brtracker.services.async.dao.AsyncDao;
import com.brtracker.shared.payload.controller.ConfigurationOverride;
import com.brtracker.shared.payload.controller.GeofenceAlertConfiguration;
import com.brtracker.shared.payload.controller.GeofencePoints;
import com.brtracker.shared.payload.controller.OverrideEntity;
import com.brtracker.shared.payload.controller.data.Account;
import com.brtracker.shared.payload.controller.data.Device;
import com.brtracker.shared.payload.controller.data.DeviceVehicleMap;
import com.brtracker.shared.payload.controller.data.Driver;
import com.brtracker.shared.payload.controller.data.Vehicle;
import com.brtracker.shared.payload.controller.lookup.AlertType;
import com.brtracker.shared.payload.controller.lookup.DeviceState;
import com.brtracker.shared.payload.tracking.AlertMessageRequest;
import com.brtracker.shared.payload.tracking.data.AlertMessage;
import com.brtracker.shared.payload.tracking.data.AlertReport;
import com.brtracker.shared.utils.DateUtil;
import com.brtracker.shared.utils.EventAuditorBuilder;
import com.brtracker.shared.utils.EventType;
import com.brtracker.shared.utils.json.JSONMapper;
import com.brtracker.shared.utils.json.JSONMapperException;
import com.brtracker.shared.utils.logging.MyLogger;
import com.brtracker.shared.utils.math.Geofence;
import com.brtracker.shared.utils.spring.HibernateHelperException;
import com.brtracker.shared.utils.wsutils.ServiceException;

public class UpdateGeofenceAlert extends AsyncAction  {

	private MyLogger logger = new MyLogger(UpdateGeofenceAlert.class, this.getClass().getSimpleName());
	private AccountWSClient accountWSClient;
	private AsyncDao dao;
	private EventAuditorBuilder eventAuditBuilder;
	
	
	@Override
	public void run () {
		try {
			super.parseMessage();
			if (deviceState.equals(DeviceState.NO_GPS)) return;
			logger.logInfo("#####################################");
			logger.logInfo("UpdateGeofenceAlert: " + message);
			// CONVERT TO DOUBLE TO CALCULATE IF IN OR OUT GEO
			double lat = Double.parseDouble(latitude);
			double lon = Double.parseDouble(longitude);
			
			// ACCOUNTWS LOOKUP
			Driver driver = accountWSClient.getDriverVehicleMapFromDevice(address).getDriver();
			DeviceVehicleMap deviceVehicleMap = accountWSClient.getDeviceVehicleMap(address);
			Device device = deviceVehicleMap.getDevice();
			Vehicle vehicle = deviceVehicleMap.getVehicle();

			String driverFullName =  driver.getFirstName() + " " + driver.getLastName();
			Account account = driver.getAccount();
			String accountLocale = account.getLocale();
			Long accountId = driver.getAccount().getId();
			
			// GET GEOFENCE ALERTS FOR THIS ACCOUNT
			List<GeofenceAlertConfiguration> geofenceConfigList =  accountWSClient.getGeofenceAlertConfiguration(accountId);

			// IF NO GEOFENCE RULES FOUND, LEAVE
			if (geofenceConfigList.isEmpty()) {
				logger.logInfo("No geofence rules found");
				return;
			}
			
			// LOOP GEOFENCE CONFIG LIST
			for (GeofenceAlertConfiguration list : geofenceConfigList) {
				
				RegionalSettings regSett = RegionalSettingsHelper.get(account, list.getUser());
				TimeZone timezone = regSett.getTimezone();
				Locale locale = regSett.getLocale();
				String accTime = DateUtil.applyTimeZoneOffset(deviceTime,  timezone, "EEE, d MMM yyyy HH:mm:ss Z", locale);
			
				
				
			if (hasAsset(list.getOverrides(), vehicle.getId())){
				logger.logInfo("PROCESSING GEOFENCE ALERT. "+ list.getDisplayName());
				int alertSeverityType = list.getAlertSeverityType().getId();
				int alertType = list.getAlertType().getId();
				String displayName = list.getDisplayName();
				Map<String, Object> notificationConfig = list.getNotificationConfiguration();
				Long userId = list.getUser().getId();
				String msgKey = list.getMessageKey();
				String nameKey = list.getNameKey();
				Long alertConfigId = list.getId();
				// GET LAT/LONG POINTS RECORDED FROM USER
				ArrayList<Double> lat_array = new ArrayList<Double>();
				ArrayList<Double> long_array = new ArrayList<Double>();

				for (GeofencePoints points : list.getPoints()) {
					lat_array.add(Double.parseDouble(points.getLatitude()));
					long_array.add(Double.parseDouble(points.getLongitude()));
				}

				// CHECK IF CURRENT LAT/LONG IS IN THE POINTS
				boolean isCoordinateIn = Geofence.is_coordinate_inside_polygon(lat,lon, lat_array, long_array);
				AlertType geoAlertType = list.getAlertType();
				boolean isAlertTypeGeoIn = geoAlertType.equals(AlertType.GEO_FENCING_IN) ? true : false;
				
				// GET ALERT MESSAGES RELATED TO THIS ALERT 
				// MEANING 
				// ALERT ALREADY RAISED.
				// DRIVER HAS GONE INSIDE AN AREA WHERE HE SHOULD NOT LEAVE
				// ETC
				
				AlertMessage alertMessage= buildAlertMessage ( userId, driver.getId(), vehicle.getId(), 
						accountId, alertType, deviceTime,  systemTime,  driverFullName, 
						displayName,   latitude,  longitude,  msgKey,  nameKey,
						alertConfigId, alertSeverityType);
				AlertMessageRequest alertMessageRequest = new AlertMessageRequest();
				alertMessageRequest.setAlertMessage(alertMessage);
				// get all alert messages that are not cleared.
				List<AlertMessage> alertMessageList =accountWSClient.getAlertMessageList(alertMessageRequest);
				
					// CHECK IF THIS VEHICLE IS PART OF THIS ALERT CONFIGURATION
					// ALERT CONFIG CAN BE APPLIED TO ACCOUNT OR SPECIFIC VEHICLES
					
						if (isAlertTypeGeoIn){ // STAY OUTSIDE
						if (isCoordinateIn) { // CURRRENT POINTS ARE INSIDE AREA MARKED BY USER
							logger.logInfo("Driver location is Inside a boundary");
							if (alertMessageList.isEmpty()){
								// RAISE ALERT AND SEND NOTIFICATION
								  logger.logInfo("Raise DB Alert and send notification");
								 alertMessage.setShowUi(true);
								 alertMessage.setRead(false);
								 alertMessage.setCleared(false);
								 dao.addOrUpdate(alertMessage);
								sendNotification(notificationConfig, "in_geofencing_alert" , accountLocale, driverFullName, displayName, accTime);
								generateAlertReport(accountId, alertSeverityType, alertType, device.getId(), latitude, longitude, displayName, deviceTime, systemTime);
							} 
						} else {
							for (AlertMessage alertM : alertMessageList) {
								logger.logInfo("Clear alerts for STAY_OUTSIDE .. id:"+alertM.getId());
							   alertM.setCleared(true);
							   dao.addOrUpdate(alertM);
							}
						}
					} else{ // ALERT TYPE MUST BE GEOFENCE OUT (STAY INSIDE)
					   if (isCoordinateIn) {
						   logger.logInfo("Driver location Inside a boundary");
						   if (alertMessageList.isEmpty()){
							   logger.logInfo("Raise DB Alert to keep track when driver goes out ");
							   alertMessage.setCleared(false);
							   alertMessage.setShowUi(false);
							   alertMessage.setRead(false);
							   dao.addOrUpdate(alertMessage);
						   }
					   } else {
						   logger.logInfo("Driver location Outside a boundary");
						   if (!alertMessageList.isEmpty()){
								 for (AlertMessage alertM : alertMessageList) {
									 logger.logInfo("Clear alerts for STAY_INSIDE .. id"+alertM.getId());
									 alertM.setCleared(true);
									 alertM.setShowUi(true);
									 alertM.setLastUpdated(deviceTime);
									 alertM.setPlaceHolders(alertMessage.getPlaceHolders());
									 dao.addOrUpdate(alertM);
								   }
								   sendNotification(notificationConfig, "out_geofencing_alert" ,accountLocale, driverFullName ,displayName ,accTime);
								   generateAlertReport(accountId, alertSeverityType, alertType, device.getId(), latitude, longitude, displayName, deviceTime, systemTime);
							 }
					   }
				   }
			} 
		} // END LOOP

		} catch (JSONMapperException e) {
			logger.logError(e.getMessage(), e);
			MqClientFactory.mqClient.sendMessageToQueue(eventAuditBuilder.addEventType(EventType.UPDATE_GEOFENCE_ALERT).addIdType(DEVICE_ADDRESS).addIdValue(null).addInput(message).addFailure(true).addOutput("JSONMapperException: "+ e.getMessage()).toString(),EVENT_AUDITOR_QUEUE);
		} catch (ServiceException e) {
			logger.logError(e.getMessage(), e);
			MqClientFactory.mqClient.sendMessageToQueue(eventAuditBuilder.addEventType(EventType.UPDATE_GEOFENCE_ALERT).addIdType(DEVICE_ADDRESS).addIdValue(null).addInput(message).addFailure(true).addOutput("ServiceException: "+ e.getMessage()).toString(), EVENT_AUDITOR_QUEUE);
		} catch (HibernateHelperException e) {
			logger.logError(e.getMessage(), e);
			MqClientFactory.mqClient.sendMessageToQueue(eventAuditBuilder.addEventType(EventType.UPDATE_GEOFENCE_ALERT).addIdType(DEVICE_ADDRESS).addIdValue(null).addInput(message).addFailure(true).addOutput("HibernateHelperException: "+ e.getMessage()).toString(),EVENT_AUDITOR_QUEUE);
		} catch (ObjectNotFoundException e) {
			logger.logError("Exception while inserting graph data.",e);
			MqClientFactory.mqClient.sendMessageToQueue(eventAuditBuilder.addEventType(EventType.UPDATE_GEOFENCE_ALERT).addIdType(DEVICE_ADDRESS).addIdValue(null).addInput(message).addFailure(true).addOutput("ObjectNotFoundException: "+ e.getMessage()).toString(),EVENT_AUDITOR_QUEUE);
		} catch (InvalidMessageException e) {
			logger.logError("Exception while inserting graph data.",e);
			MqClientFactory.mqClient.sendMessageToQueue(eventAuditBuilder.addEventType(EventType.UPDATE_DRIVER_BEHAVIOR).addIdType(DEVICE_ADDRESS).addIdValue(null).addInput(message).addFailure(true).addOutput("InvalidMessageException: "+ e.getMessage()).toString(),EVENT_AUDITOR_QUEUE);
		} 
	}
	
	private void generateAlertReport (Long accountId, int alertSeverityId , int alertTypeId, Long deviceId, String lat, String lon, String displayName, Long deviceTime, Date systemTime) throws HibernateHelperException{
		   AlertReport alertReport = new AlertReport();
		   alertReport.setAccountId(accountId);
		   alertReport.setAlertSeverityType(alertSeverityId);
		   alertReport.setAlertType(alertTypeId);
		   alertReport.setDeviceId(deviceId);
		   alertReport.setLatitude(lat);
		   alertReport.setLongitude(lon);
		   alertReport.setThreshold(displayName);
		   alertReport.setViolation(lat +","+lon);
		   alertReport.setDateCreated(deviceTime);
		   alertReport.setLastUpdated(deviceTime);
		   alertReport.setSystemTime(systemTime);
		   dao.addOrUpdate(alertReport);
	}
	private boolean hasAsset (List<ConfigurationOverride> overrides, Long id){
		if (overrides.isEmpty()) return true; // if empty, account level. 
		logger.logInfo("Alert Configuration has overrites. looking for asset id"+ id);
		for (ConfigurationOverride override : overrides){
			boolean isasset = override.getOverrideEntity().equals(OverrideEntity.Vehicle);
			
			if (isasset){
				logger.logInfo("overrite type is asset");
				boolean isAssetOverritten = override.getEntityIds().contains(id);
				if (isAssetOverritten) {
					logger.logInfo("overrite asset found!! raise alert..");
					return true;
				}
			}  else {
				logger.logInfo("overrite type not supported, skip alert");
			}
		}
		return false;
	}
	
	private void sendNotification (Map<String, Object> map , String templateKey,  String locale, String...args) {
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
			logger.logInfo("Sending geofence notification: "+notificationPayload);
			MqClientFactory.mqClient.sendMessageToQueue(notification, NOTIFICATION);
			}
		}  catch (JSONMapperException e) {
			logger.logError(e.getMessage(), e);
		}
	}
	
	private AlertMessage buildAlertMessage (Long userId, Long driverId, Long vehicleId, 
		Long accountId, int alertTypeId, Long deviceTime, Date systemTime, String driverFulName, 
		String displayName, String lat, String lon, String msgKey, String nameKey,
		Long alertConfigId, int alertSeverityId) throws JSONMapperException {
		AlertMessage alertMessage = new AlertMessage();
		alertMessage.setCleared(false);
		alertMessage.setAcknowledged(null);
		alertMessage.setUserId(userId);
		alertMessage.setDeleted(false);
		alertMessage.setRead(null);
		alertMessage.setDriverId(driverId);
		alertMessage.setVehicleId(vehicleId);
		alertMessage.setAccountId(accountId);
		alertMessage.setAlertTypeId(alertTypeId);
		alertMessage.setDateCreated(deviceTime);
		alertMessage.setLastUpdated(deviceTime);
		alertMessage.setSystemTime(systemTime);
		List<String> att = new ArrayList<String>();
		att.add(driverFulName);
		att.add(displayName);
		att.add(String.valueOf(deviceTime));
		att.add(lat);
		att.add(lon);
		alertMessage.setPlaceHolders(JSONMapper.toString(att));
		alertMessage.setMessageKey(msgKey);
		alertMessage.setNameKey(nameKey);
		alertMessage.setDisplayName(displayName);
		alertMessage.setAlertConfigId(alertConfigId);
		alertMessage.setAlertSeverityId(alertSeverityId);
		return alertMessage;
		
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
