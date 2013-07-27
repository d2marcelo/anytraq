package com.brtracker.services.async;

import javassist.tools.rmi.ObjectNotFoundException;
import com.brtracker.services.async.dao.AsyncDao;
import com.brtracker.shared.payload.controller.data.Device;
import com.brtracker.shared.payload.controller.lookup.DeviceState;
import com.brtracker.shared.payload.tracking.data.IdleReport;
import com.brtracker.shared.utils.EventAuditorBuilder;
import com.brtracker.shared.utils.EventType;
import com.brtracker.shared.utils.json.JSONMapperException;
import com.brtracker.shared.utils.logging.MyLogger;
import com.brtracker.shared.utils.spring.HibernateHelperException;


public class UpdateDeviceIdleTime extends AsyncAction {

	private MyLogger logger = new MyLogger(UpdateDeviceIdleTime.class, this.getClass().getSimpleName());
	private AccountWSClient accountWSClient;
	private AsyncDao dao;
	private EventAuditorBuilder eventAuditBuilder;
	
	
	@Override
	public void run () {
		logger.logInfo("UpdateDeviceIdleTime. " +message);
		try {
			parseMessage();
			if (deviceState.equals(DeviceState.NO_GPS)) return;
			Device device = accountWSClient.getDevice(address);
			IdleReport previousIdleReport = dao.previousIdleReport(device.getId());
			boolean deviceIsMoving = isDeviceMoving();
			if (previousIdleReport == null){
				if (deviceIsMoving) return;
				// nothing there , insert new record since asset is stopped
				previousIdleReport = new IdleReport();
				previousIdleReport.setLatitude(latitude);
				previousIdleReport.setLongitude(longitude);
				previousIdleReport.setDateCreated(deviceTime);
				previousIdleReport.setLastUpdated(deviceTime);
				previousIdleReport.setSystemTime(systemTime);
				previousIdleReport.setTripStarted(false);
				previousIdleReport.setIdleTime(deviceTime);
				previousIdleReport.setDeviceId(device.getId());
				previousIdleReport.setAccountId(device.getAccount().getId());
				dao.addOrUpdate(previousIdleReport);
			} else {
				// check if moving, then close the loop
				if (deviceIsMoving){
					previousIdleReport.setTripStarted(true);
					previousIdleReport.setIdleTime(deviceTime - previousIdleReport.getDateCreated());
					previousIdleReport.setLastUpdated(deviceTime);
					dao.addOrUpdate(previousIdleReport);
				}
			}
			
		} catch (JSONMapperException e) {
			logger.logError("Exception while parsing json.",e);
			MqClientFactory.mqClient.sendMessageToQueue(eventAuditBuilder.addEventType(EventType.UPDATE_DEVICE_IDLE_TIME).addIdType(DEVICE_ADDRESS).addIdValue(null).addInput(message).addFailure(true).addOutput("JSONMapperException: "+ e.getMessage()).toString(),EVENT_AUDITOR_QUEUE);
		} catch (HibernateHelperException e) {
			logger.logError("Exception while inserting graph data.",e);
			MqClientFactory.mqClient.sendMessageToQueue(eventAuditBuilder.addEventType(EventType.UPDATE_DEVICE_IDLE_TIME).addIdType(DEVICE_ADDRESS).addIdValue(null).addInput(message).addFailure(true).addOutput("HibernateHelperException: "+ e.getMessage()).toString(),EVENT_AUDITOR_QUEUE);
		}  catch (ObjectNotFoundException e) {
			logger.logError(e.getMessage(), e);
			MqClientFactory.mqClient.sendMessageToQueue(eventAuditBuilder.addEventType(EventType.UPDATE_DEVICE_IDLE_TIME).addIdType(DEVICE_ADDRESS).addIdValue(null).addInput(message).addFailure(true).addOutput("ObjectNotFoundException: "+ e.getMessage()).toString(), EVENT_AUDITOR_QUEUE);
		} catch (InvalidMessageException e) {
			logger.logError(e.getMessage(), e);
			MqClientFactory.mqClient.sendMessageToQueue(eventAuditBuilder.addEventType(EventType.UPDATE_DEVICE_IDLE_TIME).addIdType(DEVICE_ADDRESS).addIdValue(null).addInput(message).addFailure(true).addOutput("InvalidMessageException: "+ e.getMessage()).toString(), EVENT_AUDITOR_QUEUE);
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
