package com.brtracker.services.async;

import javassist.tools.rmi.ObjectNotFoundException;
import com.brtracker.services.async.dao.AsyncDao;
import com.brtracker.shared.payload.controller.data.Device;
import com.brtracker.shared.payload.controller.lookup.DeviceState;
import com.brtracker.shared.payload.tracking.data.DeviceHistory;
import com.brtracker.shared.payload.tracking.lookup.DeviceMessageAttributeType;
import com.brtracker.shared.utils.DateUtil;
import com.brtracker.shared.utils.EventAuditorBuilder;
import com.brtracker.shared.utils.EventType;
import com.brtracker.shared.utils.json.JSONMapper;
import com.brtracker.shared.utils.json.JSONMapperException;
import com.brtracker.shared.utils.logging.MyLogger;
import com.brtracker.shared.utils.spring.HibernateHelperException;

public class UpdateDeviceHistory  extends AsyncAction {

	private MyLogger logger = new MyLogger(UpdateDeviceHistory.class, this.getClass().getSimpleName());
	private AccountWSClient accountWSClient;
	private AsyncDao dao;
	private EventAuditorBuilder eventAuditBuilder;
	
	@Override
	public void run() {
		logger.logInfo("Add device history. " +message);
		try {
			parseMessage();
			Device device = accountWSClient.getDevice(address);
			if (deviceState.equals(DeviceState.NO_GPS)) return;
			logger.logInfo("Get Previous Device History");
			DeviceHistory previousDeviceHistory = dao.previousDeviceHistory(device.getId());
			logger.logInfo("Previous Device History? "+ (previousDeviceHistory != null ));
			// get current states
				boolean deviceIsMoving = isDeviceMoving();
				boolean deviceIsStopped = isDeviceStopped();
				boolean tripHasStarted = hasTripStarted();
				boolean tripHasEnded = hasTripEnded();
				// build history object
			DeviceHistory deviceHistory = buildDeviceHistory(deviceIsMoving, device.getId());
				// if there no history? , just insert msg.
			if (previousDeviceHistory == null) {
					logger.logInfo("No previous history, just adding a new record");
					if (super.hasMedia)
						super.updateMediaAttribute(deviceHistory);
					dao.addOrUpdate(deviceHistory);
					return;
			}
				// we have history, lets verify what we need to do..
				DeviceState oldDeviceState = getState(previousDeviceHistory.getAttributes());
				boolean deviceHasMoved = super.hasDeviceMoved(previousDeviceHistory.getLatitude(),previousDeviceHistory.getLongitude());
				boolean p_DeviceIsMoving = isDeviceMoving(oldDeviceState);
				boolean p_deviceIsStopped = isDeviceStopped(oldDeviceState);
				boolean p_tripHasStarted = hasTripStarted(oldDeviceState);
				boolean p_tripHasEnded = hasTripEnded(oldDeviceState);
				boolean p_tripHasEndedStarted = hasTripEndedStarted(oldDeviceState);
				
				
			// see device state combination for details
			if (deviceHasMoved) {
				logger.logInfo("=== DEVICE HAS MOVED ===");
				// NORMAL FLOW
				if (super.hasMedia)
				super.updateMediaAttribute(deviceHistory);
				
				if (deviceIsMoving && p_tripHasStarted ){
					logger.logInfo("Condition Match 1.1" );
					dao.addOrUpdate(deviceHistory);
				}
				else if (deviceIsStopped && p_DeviceIsMoving){
					logger.logInfo("Condition Match 1.2" );
					dao.addOrUpdate(deviceHistory);
				}
				else if (tripHasEnded && p_DeviceIsMoving){
					logger.logInfo("Condition Match 1.3" );
					dao.addOrUpdate(deviceHistory);
				}
				else if (tripHasEnded && p_deviceIsStopped){
					logger.logInfo("Condition Match 1.4" );
					dao.addOrUpdate(deviceHistory);
				}
				else if (tripHasStarted && p_tripHasEnded){
					logger.logInfo("Condition Match 1.5" );
					//skip
				}
				// VARIATIONS
				// TRIP START
				else if (tripHasStarted && p_tripHasStarted){
					logger.logInfo("Condition Match 1.6" );
					//skip
				}
				else if (tripHasStarted && p_DeviceIsMoving){
					logger.logInfo("Condition Match 1.7" );
					//skip
				}
				else if (tripHasStarted && p_deviceIsStopped){
					logger.logInfo("Condition Match 1.8" );
					//skip
				}
				else if (tripHasStarted && p_tripHasEndedStarted){
					logger.logInfo("Condition Match 1.9" );
					//skip
				}
				// TRIP ENDED
				else if (tripHasEnded && p_tripHasStarted){
					logger.logInfo("Condition Match 1.10" );
					dao.addOrUpdate(deviceHistory);
				}
				else if (tripHasEnded && p_tripHasEnded){
					logger.logInfo("Condition Match 1.11" );
					//skip
				}
				else if (tripHasEnded && p_tripHasEndedStarted){
					logger.logInfo("Condition Match 1.12" );
					//skip
				}
				// MOVING
				else if (deviceIsMoving && p_DeviceIsMoving){
					logger.logInfo("Condition Match 1.13" );
					dao.addOrUpdate(deviceHistory);
				}
				else if (deviceIsMoving && p_deviceIsStopped){
					logger.logInfo("Condition Match 1.14" );
					dao.addOrUpdate(deviceHistory);
				}
				else if (deviceIsMoving && p_tripHasEnded){
					logger.logInfo("Condition Match 1.15" );
					super.updateAttribute(DeviceMessageAttributeType.STATE, DeviceState.TRIP_STARTED.name());
					deviceHistory.setAttributes(JSONMapper.toString(deviceAttributes));
					dao.addOrUpdate(deviceHistory);
				}
				else if (deviceIsMoving && p_tripHasEndedStarted){
					logger.logInfo("Condition Match 1.16" );
					super.updateAttribute(DeviceMessageAttributeType.STATE, DeviceState.TRIP_STARTED.name());
					deviceHistory.setAttributes(JSONMapper.toString(deviceAttributes));
					dao.addOrUpdate(deviceHistory);
				}
				// STOPPED
				else if (deviceIsStopped && p_deviceIsStopped){
					logger.logInfo("Condition Match 1.17" );
					dao.addOrUpdate(deviceHistory);
				}
				else if (deviceIsStopped && p_tripHasEnded){
					logger.logInfo("Condition Match 1.18" );
					dao.addOrUpdate(deviceHistory);
				}
				else if (deviceIsStopped && p_tripHasStarted){
					logger.logInfo("Condition Match 1.19" );
					dao.addOrUpdate(deviceHistory);
				}
				else if (deviceIsStopped && p_tripHasEndedStarted){
					logger.logInfo("Condition Match 1.20" );
					dao.addOrUpdate(deviceHistory);
				}
				
			} else {
				logger.logInfo("=== DEVICE HAS NOT MOVED ===");
				if (super.hasMedia){
					super.updateMediaAttribute(previousDeviceHistory);
				}
				// NORMAL FLOW
				if (deviceIsMoving && p_tripHasStarted ){
					logger.logInfo("Condition Match 2.1" );
					//skip
				}
				else if (deviceIsStopped && p_DeviceIsMoving){
					logger.logInfo("Condition Match 2.2" );
					previousDeviceHistory.setLastUpdated(deviceTime);
					dao.addOrUpdate(previousDeviceHistory);
				}
				else if (tripHasEnded && p_DeviceIsMoving){
					logger.logInfo("Condition Match 2.3" );
					previousDeviceHistory.setLastUpdated(deviceTime);
					dao.addOrUpdate(previousDeviceHistory);
				}
				else if (tripHasEnded && p_deviceIsStopped){
					logger.logInfo("Condition Match 2.4" );
					previousDeviceHistory.setLastUpdated(deviceTime);
					dao.addOrUpdate(previousDeviceHistory);
				}
				else if (tripHasStarted && p_tripHasEnded){
					logger.logInfo("Condition Match 2.5" );
					super.updateAttribute(previousDeviceHistory, DeviceMessageAttributeType.STATE, DeviceState.TRIP_END_START.name());
					previousDeviceHistory.setLastUpdated(deviceTime);
					dao.addOrUpdate(previousDeviceHistory);
				}
				
				// VARIATIONS
				// TRIP START
				else if (tripHasStarted && p_tripHasStarted){
					logger.logInfo("Condition Match 2.6" );
					//skip
				}
				else if (tripHasStarted && p_DeviceIsMoving){
					logger.logInfo("Condition Match 2.7" );
					super.updateAttribute(previousDeviceHistory, DeviceMessageAttributeType.STATE, DeviceState.TRIP_END_START.name());
					previousDeviceHistory.setLastUpdated(deviceTime);
					dao.addOrUpdate(previousDeviceHistory);
				}
				
				else if (tripHasStarted && p_deviceIsStopped){
					logger.logInfo("Condition Match 2.8" );
					super.updateAttribute(previousDeviceHistory, DeviceMessageAttributeType.STATE, DeviceState.TRIP_END_START.name());
					previousDeviceHistory.setLastUpdated(deviceTime);
					dao.addOrUpdate(previousDeviceHistory);
				}
				else if (tripHasStarted && p_tripHasEndedStarted){
					logger.logInfo("Condition Match 2.9" );
					//skip
				}
				// TRIP ENDED
				else if (tripHasEnded && p_tripHasStarted){
					logger.logInfo("Condition Match 2.10" );
					dao.addOrUpdate(deviceHistory);
				}
				else if (tripHasEnded && p_tripHasEnded){
					logger.logInfo("Condition Match 2.11" );
					//skip
				}
				else if (tripHasEnded && p_tripHasEndedStarted){
					logger.logInfo("Condition Match 2.12" );
					//skip
				}
				// MOVING
				else if (deviceIsMoving && p_DeviceIsMoving){
					logger.logInfo("Condition Match 2.13" );
					dao.addOrUpdate(deviceHistory);
				}
				else if (deviceIsMoving && p_deviceIsStopped){
					logger.logInfo("Condition Match 2.14" );
					dao.addOrUpdate(deviceHistory);
				}
				else if (deviceIsMoving && p_tripHasEnded){
					logger.logInfo("Condition Match 2.15" );
					super.updateAttribute(DeviceMessageAttributeType.STATE, DeviceState.TRIP_STARTED.name());
					deviceHistory.setAttributes(JSONMapper.toString(deviceAttributes));
					dao.addOrUpdate(deviceHistory);
				}
				else if (deviceIsMoving && p_tripHasEndedStarted){
					logger.logInfo("Condition Match 2.16" );
					super.updateAttribute(DeviceMessageAttributeType.STATE, DeviceState.TRIP_STARTED.name());
					deviceHistory.setAttributes(JSONMapper.toString(deviceAttributes));
					dao.addOrUpdate(deviceHistory);
				}
				// STOPPED
				else if (deviceIsStopped && p_deviceIsStopped){
					logger.logInfo("Condition Match 2.17" );
					previousDeviceHistory.setLastUpdated(deviceTime);
					dao.addOrUpdate(previousDeviceHistory);
				}
				else if (deviceIsStopped && p_tripHasEnded){
					logger.logInfo("Condition Match 2.18" );
					//skip
				}
				else if (deviceIsStopped && p_tripHasStarted){
					logger.logInfo("Condition Match 2.19" );
					//skip
				}
				else if (deviceIsStopped && p_tripHasEndedStarted){
					logger.logInfo("Condition Match 2.20" );
					//skip
				}
			}
			
		} catch (JSONMapperException e) {
			logger.logError("Exception while parsing json.",e);
			MqClientFactory.mqClient.sendMessageToQueue(eventAuditBuilder.addEventType(EventType.UPDATE_DEVICE_HISTORY).addIdType(DEVICE_ADDRESS).addIdValue(null).addInput(message).addFailure(true).addOutput("JSONMapperException: "+ e.getMessage()).toString(), EVENT_AUDITOR_QUEUE);
		} catch (HibernateHelperException e) {
			logger.logError("Exception while inserting device history  data.",e);
			MqClientFactory.mqClient.sendMessageToQueue(eventAuditBuilder.addEventType(EventType.UPDATE_DEVICE_HISTORY).addIdType(DEVICE_ADDRESS).addIdValue(null).addInput(message).addFailure(true).addOutput("HibernateHelperException: "+ e.getMessage()).toString(),EVENT_AUDITOR_QUEUE);
		} catch (ObjectNotFoundException e) {
			logger.logError(e.getMessage(), e);
			MqClientFactory.mqClient.sendMessageToQueue(eventAuditBuilder.addEventType(EventType.UPDATE_DEVICE_HISTORY).addIdType(DEVICE_ADDRESS).addIdValue(null).addInput(message).addFailure(true).addOutput("ObjectNotFoundException: "+ e.getMessage()).toString(),EVENT_AUDITOR_QUEUE);
		} catch (InvalidMessageException e) {
			logger.logError(e.getMessage(), e);
			MqClientFactory.mqClient.sendMessageToQueue(eventAuditBuilder.addEventType(EventType.UPDATE_DEVICE_HISTORY).addIdType(DEVICE_ADDRESS).addIdValue(null).addInput(message).addFailure(true).addOutput("InvalidMessageException: "+ e.getMessage()).toString(),EVENT_AUDITOR_QUEUE);
		}
	}

	
	private DeviceHistory buildDeviceHistory (boolean deviceMoving, Long deviceId) throws JSONMapperException{
		DeviceHistory deviceHistory = new DeviceHistory();
		deviceHistory.setMoving(deviceMoving);
		deviceHistory.setSearchDate(DateUtil.getDate(deviceTime));
		deviceHistory.setDateCreated(deviceTime);
		deviceHistory.setLastUpdated(deviceTime);
		deviceHistory.setSystemTime(systemTime);
		deviceHistory.setDeviceId(deviceId);
		deviceHistory.setLatitude(latitude);
		deviceHistory.setLongitude(longitude);
		deviceHistory.setAttributes(JSONMapper.toString(deviceAttributes));
		return deviceHistory;
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
