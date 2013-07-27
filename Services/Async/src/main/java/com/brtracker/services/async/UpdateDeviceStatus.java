package com.brtracker.services.async;

import java.util.HashMap;
import java.util.Map;
import javassist.tools.rmi.ObjectNotFoundException;
import com.brtracker.services.async.dao.AsyncDao;
import com.brtracker.shared.payload.controller.data.Device;
import com.brtracker.shared.payload.tracking.data.DeviceStatus;
import com.brtracker.shared.utils.EventAuditorBuilder;
import com.brtracker.shared.utils.EventType;
import com.brtracker.shared.utils.json.JSONMapper;
import com.brtracker.shared.utils.json.JSONMapperException;
import com.brtracker.shared.utils.logging.MyLogger;
import com.brtracker.shared.utils.spring.HibernateHelperException;

public class UpdateDeviceStatus extends AsyncAction  {

	private MyLogger logger = new MyLogger(UpdateDeviceStatus.class,this.getClass().getSimpleName());
	private AccountWSClient accountWSClient;
	private AsyncDao dao;
	private EventAuditorBuilder eventAuditBuilder;
	
	@Override
	public void run() {
		try{
		logger.logInfo("Update Device Status:"+message);
		parseMessage();
		Device device = accountWSClient.getDevice(address);
		DeviceStatus deviceStatus = dao.getDeviceStatus(device.getAddress());
		Map<String,String> att = new HashMap<String,String>();
		if (deviceStatus == null) {
			deviceStatus = new DeviceStatus();
			deviceStatus.setDateCreated(deviceTime);
		} else {
			att = (Map<String, String>) JSONMapper.toObject(deviceStatus.getAttributes(), Map.class);
		}
		
		for (Map.Entry<String, String> m : deviceAttributes.entrySet()){
			att.put(m.getKey(), m.getValue());
		}
		deviceStatus.setAttributes(JSONMapper.toString(att));
		deviceStatus.setDeviceAddress(device.getAddress());
		deviceStatus.setDateCreated(deviceTime);
		deviceStatus.setAccountName(device.getAccount().getName());
		deviceStatus.setAffiliateId(device.getAccount().getAffiliate().getId());
		deviceStatus.setLastUpdated(deviceTime);
		deviceStatus.setSystemTime(systemTime);
		dao.addOrUpdate(deviceStatus);
		} catch (JSONMapperException e){
			logger.logError(e.getMessage(), e);
			MqClientFactory.mqClient.sendMessageToQueue(eventAuditBuilder.addEventType(EventType.UPDATE_DEVICE_STATUS).addIdType(DEVICE_ADDRESS).addIdValue(null).addInput(message).addFailure(true).addOutput("JSONMapperException: "+ e.getMessage()).toString(),EVENT_AUDITOR_QUEUE);
		} catch (HibernateHelperException e) {
			logger.logError(e.getMessage(), e);
			MqClientFactory.mqClient.sendMessageToQueue(eventAuditBuilder.addEventType(EventType.UPDATE_DEVICE_STATUS).addIdType(DEVICE_ADDRESS).addIdValue(null).addInput(message).addFailure(true).addOutput("HibernateHelperException: "+ e.getMessage()).toString(),EVENT_AUDITOR_QUEUE);
		} catch (ObjectNotFoundException e) {
			logger.logError(e.getMessage(), e);
			MqClientFactory.mqClient.sendMessageToQueue(eventAuditBuilder.addEventType(EventType.UPDATE_DEVICE_STATUS).addIdType(DEVICE_ADDRESS).addIdValue(null).addInput(message).addFailure(true).addOutput("ObjectNotFoundException: "+ e.getMessage()).toString(), EVENT_AUDITOR_QUEUE);
		} catch (InvalidMessageException e) {
			logger.logError(e.getMessage(), e);
			MqClientFactory.mqClient.sendMessageToQueue(eventAuditBuilder.addEventType(EventType.UPDATE_DEVICE_STATUS).addIdType(DEVICE_ADDRESS).addIdValue(null).addInput(message).addFailure(true).addOutput("InvalidMessageException: "+ e.getMessage()).toString(), EVENT_AUDITOR_QUEUE);
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
