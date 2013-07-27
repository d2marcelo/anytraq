package com.brtracker.services.async;

import java.util.Date;
import com.brtracker.services.async.dao.AsyncDao;
import com.brtracker.shared.payload.tracking.data.DeviceState;
import com.brtracker.shared.utils.EventAuditorBuilder;
import com.brtracker.shared.utils.EventType;
import com.brtracker.shared.utils.logging.MyLogger;

public class UpdateDeviceState extends AsyncAction {

	private MyLogger logger = new MyLogger(UpdateDeviceState.class, this.getClass().getSimpleName());
	private AsyncDao dao;
	private EventAuditorBuilder eventAuditBuilder;
	
	
	@Override
	public void run() {
		try{
		logger.logInfo("Update Device Status:"+message);
		parseMessage();
		DeviceState state = new DeviceState();
		state.setLatitude(super.latitude);
		state.setLongitude(super.longitude);
		state.setState(super.deviceState.name());
		state.setSystemTime(new Date());
		state.setUnit_time(super.deviceTime);
		state.setDistance(super.distance);
		state.setSpeed(super.speed);
		state.setUnitAddress(super.address);
		dao.addOrUpdate(state);
		} catch (Exception e){
			logger.logError(e.getMessage(), e);
			MqClientFactory.mqClient.sendMessageToQueue(eventAuditBuilder.addEventType(EventType.UPDATE_ASYNC_AUDIT).addIdType(DEVICE_ADDRESS).addIdValue(null).addInput(message).addFailure(true).addOutput("Exception: "+ e.getMessage()).toString(), EVENT_AUDITOR_QUEUE);
		}
		
	}


	public EventAuditorBuilder getEventAuditBuilder() {
		return eventAuditBuilder;
	}


	public void setEventAuditBuilder(EventAuditorBuilder eventAuditBuilder) {
		this.eventAuditBuilder = eventAuditBuilder;
	}


	public AsyncDao getDao() {
		return dao;
	}


	public void setDao(AsyncDao dao) {
		this.dao = dao;
	}
	
	
}
