package com.brtracker.services.async;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javassist.tools.rmi.ObjectNotFoundException;
import com.brtracker.services.async.dao.AsyncDao;
import com.brtracker.shared.payload.controller.AlertConfigurationRequest;
import com.brtracker.shared.payload.controller.ControllerInterfaceLookup;
import com.brtracker.shared.payload.controller.DefaultAlertConfiguration;
import com.brtracker.shared.payload.controller.DefaultAlertConfigurationResponse;
import com.brtracker.shared.payload.controller.DriverVehicleMapRequest;
import com.brtracker.shared.payload.controller.DriverVehicleMapResponse;
import com.brtracker.shared.payload.controller.data.Driver;
import com.brtracker.shared.payload.controller.data.DriverVehicleMap;
import com.brtracker.shared.payload.controller.data.Vehicle;
import com.brtracker.shared.payload.controller.lookup.AlertCategoryType;
import com.brtracker.shared.payload.controller.lookup.AlertType;
import com.brtracker.shared.payload.controller.lookup.DeviceState;
import com.brtracker.shared.payload.tracking.data.AlertMessage;
import com.brtracker.shared.utils.EventAuditorBuilder;
import com.brtracker.shared.utils.EventType;
import com.brtracker.shared.utils.json.JSONMapper;
import com.brtracker.shared.utils.json.JSONMapperException;
import com.brtracker.shared.utils.logging.MyLogger;
import com.brtracker.shared.utils.spring.HibernateHelperException;
import com.brtracker.shared.utils.wsutils.ClientCall;
import com.brtracker.shared.utils.wsutils.ServiceException;


public class UpdateDriverAlert extends AsyncAction {

	private MyLogger logger = new MyLogger(UpdateDriverAlert.class, this.getClass().getSimpleName());
	private Map<String,String> alertList = new HashMap<String,String>();
	private String CONTROLLER_IP = ServiceLookup.getControllerIP();
	private String UIAlertURL = ServiceLookup.getUIAlert();
	private AccountWSClient accountWSClient;
	private AsyncDao dao;
	private EventAuditorBuilder eventAuditBuilder;
	
	
	@Override
	public void run () {
		try {
			logger.logInfo("UpdateDriverAlert: "+message);
			parseMessage();
			if (deviceState.equals(DeviceState.NO_GPS)) return;
			Driver driver = accountWSClient.getDriverVehicleMapFromDevice(address).getDriver();
			// get a list of alert configuration (should be memcached)
			AlertConfigurationRequest acr = new AlertConfigurationRequest();
			Long accountId = driver.getAccount().getId();
		
			acr.setAccountId(accountId);
			acr.setAlertCategoryType(AlertCategoryType.DRIVER);
			String configUrl = ControllerInterfaceLookup.getURLPath(ControllerInterfaceLookup.defaultAlertConfiguration_LIST,  CONTROLLER_IP);
			String configPayload= JSONMapper.toString(acr);
			logger.logInfo("List alert configurations for driver: "+configUrl+ " payload: "+configPayload);
			String alertConfigStr =  ClientCall.post(configUrl, configPayload);
			DefaultAlertConfigurationResponse resp = (DefaultAlertConfigurationResponse) JSONMapper.toObject(alertConfigStr, DefaultAlertConfigurationResponse.class);
			List<DefaultAlertConfiguration> listAlertConfig = resp.getDefaultAlertConfiguration();
			if (listAlertConfig.isEmpty()) {
				logger.logInfo("No driver alert configuration found:"+ alertConfigStr);
				return;
			}
			addAlert(AlertType.DRIVER_OVER_SPEEDING.name(),super.overSpeeding);
			addAlert(AlertType.DRIVER_SHARP_BREAKING.name(),super.sharpBreaking);
			addAlert(AlertType.DRIVER_SHARP_LANE_CROSS.name(),super.sharpLaneCross);
			addAlert(AlertType.DRIVER_SHARP_TURNS.name(),super.sharpTurns);
			addAlert(AlertType.DRIVER_QUICK_ACCEL.name(),super.quickAccel);
			
			if (alertList.isEmpty()) {
				logger.logInfo("No driver alerts found");
				return ;
			}
			
			
			// get vehicle for this driver (should be memcached)
			
			DriverVehicleMapRequest dvr = new DriverVehicleMapRequest();
			DriverVehicleMap dvm = new DriverVehicleMap();
			dvm.setDriver(driver);
			dvr.setDriverVehicleMap(dvm);
			String url = ControllerInterfaceLookup.getURLPath(ControllerInterfaceLookup.driverVehicleMap_LIST, CONTROLLER_IP);
			String vehiclePayload = JSONMapper.toString(dvr);
			logger.logInfo("Locating Driver's Vehicle: "+url +" payload: "+vehiclePayload);
			String vehicleMapStr =  ClientCall.post(url, vehiclePayload);
			logger.logInfo(vehicleMapStr);
			DriverVehicleMapResponse dvmResponse = (DriverVehicleMapResponse) JSONMapper.toObject(vehicleMapStr, DriverVehicleMapResponse.class);
			List<DriverVehicleMap> dvmList = dvmResponse.getDriverVehicleMapList();
			if (dvmList.isEmpty()) {
				logger.logInfo("Unable to find vehicle for driver:"+driver.getId());
				return;
			}
			
			Vehicle vehicle = dvmList.get(0).getVehicle();
			
			// build UI Notification
			String UIAlertEndpoint = UIAlertURL;
			
			// build List of AlertMessage to commit
			for (DefaultAlertConfiguration config: listAlertConfig){
				logger.logInfo("Processing alert:"+config.getAlertType().name());
				if (alertList.containsKey(config.getAlertType().name())){
					logger.logInfo("Driver Alert Detected: "+config.getAlertType().name());
					// raise alert in db
					
					AlertMessage alertMessage = new AlertMessage();
					alertMessage.setAccountId(config.getAccountId());
					alertMessage.setUserId(config.getUser().getId());
					alertMessage.setAcknUserId(null);
					alertMessage.setDisplayName(config.getDisplayName());
					alertMessage.setAlertSeverityId(config.getAlertSeverityType().getId());
					alertMessage.setAlertTypeId(config.getAlertType().getId());
					alertMessage.setDateCreated(deviceTime);
					alertMessage.setSystemTime(systemTime);
					alertMessage.setLastUpdated(deviceTime);
					alertMessage.setDriverId(driver.getId());
					alertMessage.setMessageKey(config.getMessageKey());
					alertMessage.setNameKey(config.getNameKey());
					alertMessage.setVehicleId(vehicle.getId());
					alertMessage.setCleared(false);
					alertMessage.setAcknowledged(false);
					alertMessage.setDeleted(false);
					alertMessage.setRead(false);
					// need to build placeholders
					List<String> list = new ArrayList<String>();
					list.add(driver.getFirstName() +" "+driver.getLastName());
					list.add(latitude);
					list.add(longitude);
					alertMessage.setPlaceHolders(JSONMapper.toString(list));
					dao.addOrUpdate(alertMessage);
					
					// notify UI
					String UIEndpoint  = UIAlertEndpoint +"?alertId="+alertMessage.getId() +"&severity="+alertMessage.getAlertSeverityId()+"&accountId="+accountId;
					logger.logInfo("Notifying UI:  "+UIEndpoint);
					String output = ClientCall.get(UIEndpoint);
					logger.logInfo(output);
				}
			}
			
			
		} catch (JSONMapperException e) {
			logger.logError(e.getMessage(), e);
			MqClientFactory.mqClient.sendMessageToQueue(eventAuditBuilder.addEventType(EventType.UPDATE_DRIVER_ALERT).addIdType(DEVICE_ADDRESS).addIdValue(null).addInput(message).addFailure(true).addOutput("JSONMapperException: "+ e.getMessage()).toString(),EVENT_AUDITOR_QUEUE);
		} catch (ServiceException e) {
			logger.logError(e.getMessage(), e);
			MqClientFactory.mqClient.sendMessageToQueue(eventAuditBuilder.addEventType(EventType.UPDATE_DRIVER_ALERT).addIdType(DEVICE_ADDRESS).addIdValue(null).addInput(message).addFailure(true).addOutput("ServiceException: "+ e.getMessage()).toString(),EVENT_AUDITOR_QUEUE);
		} catch (HibernateHelperException e) {
			logger.logError(e.getMessage(), e);
			MqClientFactory.mqClient.sendMessageToQueue(eventAuditBuilder.addEventType(EventType.UPDATE_DRIVER_ALERT).addIdType(DEVICE_ADDRESS).addIdValue(null).addInput(message).addFailure(true).addOutput("HibernateHelperException: "+ e.getMessage()).toString(),EVENT_AUDITOR_QUEUE);
		} catch (ObjectNotFoundException e) {
			logger.logError(e.getMessage(), e);
			MqClientFactory.mqClient.sendMessageToQueue(eventAuditBuilder.addEventType(EventType.UPDATE_DRIVER_ALERT).addIdType(DEVICE_ADDRESS).addIdValue(null).addInput(message).addFailure(true).addOutput("ObjectNotFoundException: "+ e.getMessage()).toString(),EVENT_AUDITOR_QUEUE);
		} catch (InvalidMessageException e) {
			logger.logError("Exception while inserting graph data.",e);
			MqClientFactory.mqClient.sendMessageToQueue(eventAuditBuilder.addEventType(EventType.UPDATE_DRIVER_BEHAVIOR).addIdType(DEVICE_ADDRESS).addIdValue(null).addInput(message).addFailure(true).addOutput("InvalidMessageException: "+ e.getMessage()).toString(),EVENT_AUDITOR_QUEUE);
		}	
		
		
		
	}
	
	
	
	private void addAlert(String alertType, String value){
		if (value != null){
			logger.logInfo("Add alert:"+ alertType+ " value: "+value);
			alertList.put(alertType, value);
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
