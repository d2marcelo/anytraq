package com.brtracker.services.async;

import java.util.HashMap;
import java.util.Map;
import javassist.tools.rmi.ObjectNotFoundException;
import com.brtracker.services.async.dao.AsyncDao;
import com.brtracker.shared.payload.controller.data.Device;
import com.brtracker.shared.payload.controller.lookup.DeviceState;
import com.brtracker.shared.payload.tracking.data.DriverBehavior;
import com.brtracker.shared.payload.tracking.lookup.DriverBehaviorAttributeType;
import com.brtracker.shared.utils.EventAuditorBuilder;
import com.brtracker.shared.utils.EventType;
import com.brtracker.shared.utils.json.JSONMapper;
import com.brtracker.shared.utils.json.JSONMapperException;
import com.brtracker.shared.utils.logging.MyLogger;
import com.brtracker.shared.utils.spring.HibernateHelperException;


public class UpdateDriverBehavior extends AsyncAction {

	private MyLogger logger = new MyLogger(UpdateDriverBehavior.class, this.getClass().getSimpleName());
	private AccountWSClient accountWSClient;
	private AsyncDao dao;
	private EventAuditorBuilder eventAuditBuilder;
	
	
	@Override
	public void run () {
		
			logger.logInfo("Add DriverBehavior. " +message);
			try {
				parseMessage();
				if (deviceState.equals(DeviceState.NO_GPS)) return;
				Device device = accountWSClient.getDevice(address);
				DriverBehavior driverBehavior = new DriverBehavior();
				driverBehavior.setDeviceId(device.getId());
				driverBehavior.setDateCreated(deviceTime);
				driverBehavior.setLastUpdated(deviceTime);
				driverBehavior.setSystemTime(systemTime);
				Map<String, String> stats = new HashMap<String,String>();
				put(DriverBehaviorAttributeType.QUICK_ACCEL, super.quickAccel, stats);
				put(DriverBehaviorAttributeType.SHARP_BREAKING, super.sharpTurns, stats);
				put(DriverBehaviorAttributeType.SHARP_TURNS,super.sharpTurns, stats);
				put(DriverBehaviorAttributeType.SHARP_LANE_CROSS,super.sharpLaneCross, stats);
				driverBehavior.setLatitude(latitude);
				driverBehavior.setLongitude(longitude);
				driverBehavior.setStats(JSONMapper.toString(stats));
				if (!stats.isEmpty()){
					logger.logInfo("saving driverbehavior");
					dao.addOrUpdate(driverBehavior);
				}
			} catch (JSONMapperException e) {
				logger.logError("Exception while parsing json.",e);
				MqClientFactory.mqClient.sendMessageToQueue(eventAuditBuilder.addEventType(EventType.UPDATE_DRIVER_BEHAVIOR).addIdType(DEVICE_ADDRESS).addIdValue(null).addInput(message).addFailure(true).addOutput("JSONMapperException: "+ e.getMessage()).toString(),EVENT_AUDITOR_QUEUE);
			} catch (HibernateHelperException e) {
				logger.logError("Exception while inserting graph data.",e);
				MqClientFactory.mqClient.sendMessageToQueue(eventAuditBuilder.addEventType(EventType.UPDATE_DRIVER_BEHAVIOR).addIdType(DEVICE_ADDRESS).addIdValue(null).addInput(message).addFailure(true).addOutput("HibernateHelperException: "+ e.getMessage()).toString(),EVENT_AUDITOR_QUEUE);
			} catch (ObjectNotFoundException e) {
				logger.logError("Exception while inserting graph data.",e);
				MqClientFactory.mqClient.sendMessageToQueue(eventAuditBuilder.addEventType(EventType.UPDATE_DRIVER_BEHAVIOR).addIdType(DEVICE_ADDRESS).addIdValue(null).addInput(message).addFailure(true).addOutput("ObjectNotFoundException: "+ e.getMessage()).toString(),EVENT_AUDITOR_QUEUE);
			} catch (InvalidMessageException e) {
				logger.logError("Exception while inserting graph data.",e);
				MqClientFactory.mqClient.sendMessageToQueue(eventAuditBuilder.addEventType(EventType.UPDATE_DRIVER_BEHAVIOR).addIdType(DEVICE_ADDRESS).addIdValue(null).addInput(message).addFailure(true).addOutput("InvalidMessageException: "+ e.getMessage()).toString(),EVENT_AUDITOR_QUEUE);
			}
			
	}
	
	private void put (String key, String value, Map<String, String> stats) throws JSONMapperException{
		logger.logInfo("key:"+ key +" value:"+ value+" map size:"+stats.size());
		if (value != null)
			stats.put(key, value);
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
