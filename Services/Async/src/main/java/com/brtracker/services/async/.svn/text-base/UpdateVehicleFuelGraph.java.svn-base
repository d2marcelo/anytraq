package com.brtracker.services.async;

import javassist.tools.rmi.ObjectNotFoundException;
import com.brtracker.services.async.dao.AsyncDao;
import com.brtracker.shared.payload.controller.data.Device;
import com.brtracker.shared.payload.controller.lookup.DeviceState;
import com.brtracker.shared.payload.tracking.data.GraphData;
import com.brtracker.shared.payload.tracking.lookup.GraphDataType;
import com.brtracker.shared.utils.EventAuditorBuilder;
import com.brtracker.shared.utils.EventType;
import com.brtracker.shared.utils.json.JSONMapperException;
import com.brtracker.shared.utils.logging.MyLogger;
import com.brtracker.shared.utils.spring.HibernateHelperException;


public class UpdateVehicleFuelGraph extends  AsyncAction {

	private MyLogger logger = new MyLogger(UpdateVehicleFuelGraph.class, this.getClass().getSimpleName());
	private AccountWSClient accountWSClient;
	private AsyncDao dao;
	private EventAuditorBuilder eventAuditBuilder;
	
	@Override
	public void run () {
		logger.logInfo("Add Trip Fuel Graph. " +message);
		try {
			super.parseMessage();
			if (deviceState.equals(DeviceState.NO_GPS)) return;
			Device device = accountWSClient.getDevice(address);
			if (fuel == null){
				logger.logInfo("Fuel attribute not available.");
				return;
			}
			
			GraphData previousGraphData = dao.previousGraphData(device.getId(), GraphDataType.FUEL.getId());
			if (previousGraphData != null){
				if (previousGraphData.getLatitude().equals(latitude) && previousGraphData.getLongitude().equals(longitude)){
				logger.logInfo("Duplicated location, skipping");
				return;
			}
			}
			
			GraphData graphData = new GraphData();
			graphData.setDateCreated(deviceTime);
			graphData.setLastUpdated(deviceTime);
			graphData.setSystemTime(systemTime);
			graphData.setGraphDataType(GraphDataType.FUEL.getId());
			graphData.setDeviceId(device.getId());
			graphData.setxData(new Double(deviceTime));
			graphData.setyData(new Double(fuel));
			graphData.setLatitude(latitude);
			graphData.setLatitude(longitude);
			dao.addOrUpdate(graphData);
			
		} catch (JSONMapperException e) {
			logger.logError("Exception while parsing json.",e);
			MqClientFactory.mqClient.sendMessageToQueue(eventAuditBuilder.addEventType(EventType.UPDATE_VEHICLE_FUEL_GRAPH).addIdType(DEVICE_ADDRESS).addIdValue(null).addInput(message).addFailure(true).addOutput("JSONMapperException: "+ e.getMessage()).toString(),EVENT_AUDITOR_QUEUE);
		} catch (HibernateHelperException e) {
			logger.logError("Exception while inserting graph data.",e);
			MqClientFactory.mqClient.sendMessageToQueue(eventAuditBuilder.addEventType(EventType.UPDATE_VEHICLE_FUEL_GRAPH).addIdType(DEVICE_ADDRESS).addIdValue(null).addInput(message).addFailure(true).addOutput("HibernateHelperException: "+ e.getMessage()).toString(),EVENT_AUDITOR_QUEUE);
		} catch (ObjectNotFoundException e) {
			logger.logError("Exception while inserting graph data.",e);
			MqClientFactory.mqClient.sendMessageToQueue(eventAuditBuilder.addEventType(EventType.UPDATE_VEHICLE_FUEL_GRAPH).addIdType(DEVICE_ADDRESS).addIdValue(null).addInput(message).addFailure(true).addOutput("ObjectNotFoundException: "+ e.getMessage()).toString(),EVENT_AUDITOR_QUEUE);
		} catch (InvalidMessageException e) {
			logger.logError("InvalidMessageException while inserting graph data.",e);
			MqClientFactory.mqClient.sendMessageToQueue(eventAuditBuilder.addEventType(EventType.UPDATE_DRIVER_BEHAVIOR).addIdType(DEVICE_ADDRESS).addIdValue(null).addInput(message).addFailure(true).addOutput("InvalidMessageException: "+ e.getMessage()).toString(),EVENT_AUDITOR_QUEUE);
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
