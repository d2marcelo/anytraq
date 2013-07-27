package com.brtracker.services.async;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javassist.tools.rmi.ObjectNotFoundException;
import com.brtracker.services.async.dao.AsyncDao;
import com.brtracker.shared.payload.controller.data.Device;
import com.brtracker.shared.payload.controller.lookup.DeviceState;
import com.brtracker.shared.payload.tracking.data.ActivityReport;
import com.brtracker.shared.payload.tracking.lookup.DeviceMessageAttributeType;
import com.brtracker.shared.utils.EventAuditorBuilder;
import com.brtracker.shared.utils.EventType;
import com.brtracker.shared.utils.json.JSONMapper;
import com.brtracker.shared.utils.json.JSONMapperException;
import com.brtracker.shared.utils.logging.MyLogger;
import com.brtracker.shared.utils.spring.HibernateHelperException;

public class UpdateActivityReport extends AsyncAction  {

	private MyLogger logger = new MyLogger(UpdateActivityReport.class, this.getClass().getSimpleName());
	private AccountWSClient accountWSClient;
	private AsyncDao dao;
	private EventAuditorBuilder eventAuditBuilder;
	
	@Override
	public void run () {
		logger.logInfo("==============================");
		logger.logInfo("Add device activity. " +message);
		try {
			logger.logInfo("parse message.");
			parseMessage();
			logger.logInfo("device state."+deviceState);
			if (deviceState.equals(DeviceState.NO_GPS)) return;
			Device device = accountWSClient.getDevice(address);
			// get previous record to append data
			ActivityReport pActivityReport = dao.previousActivityReport(device.getId());
			boolean deviceHasMoved = hasDeviceMoved(pActivityReport);
			DeviceState oldDeviceState = getDeviceState(pActivityReport);
			
			// is device moving
			boolean deviceIsMoving = isDeviceMoving();
			boolean tripHasEnded = super.hasTripEnded();
			boolean tripHasStarted = super.hasTripStarted();
			boolean deviceIsStopped = isDeviceStopped();
			boolean p_TripHasStarted = super.hasTripStarted(oldDeviceState);
			boolean p_DeviceIsMoving = super.isDeviceMoving(oldDeviceState);
			boolean p_DeviceIsStopped = super.isDeviceStopped(oldDeviceState);
			boolean p_TripHasEnded = super.hasTripEnded(oldDeviceState);
			boolean p_TripHasEndedStarted = super.hasTripEndedStarted(oldDeviceState);
			logger.logInfo("device state."+ super.deviceState.name());
			logger.logInfo("previous state " +oldDeviceState);
				
			
			if (pActivityReport == null) {
				logger.logInfo("n previous data, create new one and exit");
				dao.addOrUpdate(this.createNewActivity(device));
				return;
			} else {
				logger.logInfo("previous activity found id: "+pActivityReport.getId());
			}
					
			//======================== LOGIC 
			
			if (deviceHasMoved) {
				logger.logInfo("=== DEVICE HAS MOVED ===");
				// NORMAL FLOW
				if (deviceIsMoving && p_TripHasStarted ){
					logger.logInfo("Condition HASMOVED => TRIP START => MOVING" );
					this.updateActivity(pActivityReport);
					dao.addOrUpdate(pActivityReport);
				}
				else if (deviceIsStopped && p_DeviceIsMoving){
					logger.logInfo("Condition HASMOVED => MOVING => STOPPED" );
					this.updateActivity(pActivityReport);
					dao.addOrUpdate(pActivityReport);
				}
				else if (tripHasEnded && p_DeviceIsMoving){
					logger.logInfo("Condition HASMOVED => MOVING => TRIP ENDED" );
					this.updateActivity(pActivityReport);
					pActivityReport.setTripFinished(true);
					dao.addOrUpdate(pActivityReport);
				}
				else if (tripHasEnded && p_DeviceIsStopped){
					logger.logInfo("Condition HASMOVED => STOPPED => TRIP ENDED" );
					this.updateActivity(pActivityReport);
					pActivityReport.setTripFinished(true);
					dao.addOrUpdate(pActivityReport);
				}
				else if (tripHasStarted && p_TripHasEnded){
					logger.logInfo("Condition HASMOVED => TRIP END => TRIP START" );
					pActivityReport.setTripFinished(true);
					dao.addOrUpdate(pActivityReport);
					dao.addOrUpdate(this.createNewActivity(device));
				}
				// VARIATIONS
				// TRIP START
				else if (tripHasStarted && p_TripHasStarted){
					logger.logInfo("Condition Match 1.6" );
					//skip
				}
				else if (tripHasStarted && p_DeviceIsMoving){
					logger.logInfo("Condition HASMOVED => MOVING => TRIP START" );
					pActivityReport.setTripFinished(true);
					dao.addOrUpdate(pActivityReport);
					dao.addOrUpdate(this.createNewActivity(device));
				}
				else if (tripHasStarted && p_DeviceIsStopped){
					logger.logInfo("Condition HASMOVED => STOPPED => TRIP START" );
					pActivityReport.setTripFinished(true);
					dao.addOrUpdate(pActivityReport);
					dao.addOrUpdate(this.createNewActivity(device));
				}
				else if (tripHasStarted && p_TripHasEndedStarted){
					logger.logInfo("Condition HASMOVED => TRIP START END => TRIP START" );
					//skip
				}
				// TRIP ENDED
				else if (tripHasEnded && p_TripHasStarted){
					logger.logInfo("Condition HASMOVED => TRIP START  => TRIP END" );
					this.updateActivity(pActivityReport);
					pActivityReport.setTripFinished(true);
					dao.addOrUpdate(pActivityReport);
				}
				else if (tripHasEnded && p_TripHasEnded){
					logger.logInfo("Condition HASMOVED => TRIP END  => TRIP END" );
					//skip
				}
				else if (tripHasEnded && p_TripHasEndedStarted){
					logger.logInfo("Condition HASMOVED => TRIP END START  => TRIP END" );
					this.updateActivity(pActivityReport);
					pActivityReport.setTripFinished(true);
					dao.addOrUpdate(pActivityReport);
				}
				// MOVING
				else if (deviceIsMoving && p_DeviceIsMoving){
					logger.logInfo("Condition HASMOVED => MOVING  => MOVING" );
					this.updateActivity(pActivityReport);
					dao.addOrUpdate(pActivityReport);
				}
				else if (deviceIsMoving && p_DeviceIsStopped){
					logger.logInfo("Condition HASMOVED => STOP  => MOVING" );
					this.updateActivity(pActivityReport);
					dao.addOrUpdate(pActivityReport);
				}
				else if (deviceIsMoving && p_TripHasEnded){
					logger.logInfo("Condition HASMOVED => TRIP END  => MOVING" );
					pActivityReport.setTripFinished(true);
					dao.addOrUpdate(pActivityReport);
					dao.addOrUpdate(this.createNewActivity(device));
				}
				else if (deviceIsMoving && p_TripHasEndedStarted){
					logger.logInfo("Condition HASMOVED => TRIP START  => MOVING" );
					this.updateActivity(pActivityReport);
					dao.addOrUpdate(pActivityReport);
				}
				// STOPPED
				else if (deviceIsStopped && p_DeviceIsStopped){
					logger.logInfo("Condition HASMOVED => STOP  => STOP" );
					this.updateActivity(pActivityReport);
					dao.addOrUpdate(pActivityReport);
				}
				else if (deviceIsStopped && p_TripHasEnded){
					logger.logInfo("Condition HASMOVED => TRIP END  => STOP" );
					pActivityReport.setTripFinished(true);
					dao.addOrUpdate(pActivityReport);
					dao.addOrUpdate(this.createNewActivity(device));
				}
				else if (deviceIsStopped && p_TripHasStarted){
					logger.logInfo("Condition HASMOVED => TRIP START  => STOP" );
					this.updateActivity(pActivityReport);
					dao.addOrUpdate(pActivityReport);
				}
				else if (deviceIsStopped && p_TripHasEndedStarted){
					logger.logInfo("Condition HASMOVED => STOP  => STOP" );
					this.updateActivity(pActivityReport);
					dao.addOrUpdate(pActivityReport);
				}
				
			} else {
				logger.logInfo("=== DEVICE HAS NOT MOVED ===");
				// NORMAL FLOW
				if (deviceIsMoving && p_TripHasStarted ){
					logger.logInfo("Condition HAS_NOT_MOVED => TRIP START  => MOVING" );
					//skip
				}
				else if (deviceIsStopped && p_DeviceIsMoving){
					logger.logInfo("Condition HAS_NOT_MOVED => MOVING  => STOPPED" );
					//skip
				}
				else if (tripHasEnded && p_DeviceIsMoving){
					logger.logInfo("Condition HAS_NOT_MOVED => TRIP END  => MOVING" );
					this.updateActivity(pActivityReport);
					pActivityReport.setTripFinished(true);
					dao.addOrUpdate(pActivityReport);
				}
				else if (tripHasEnded && p_DeviceIsStopped){
					logger.logInfo("Condition HAS_NOT_MOVED => TRIP END  => STOPPED" );
					this.updateActivity(pActivityReport);
					pActivityReport.setTripFinished(true);
					dao.addOrUpdate(pActivityReport);
				}
				else if (tripHasStarted && p_TripHasEnded){
					logger.logInfo("Condition HAS_NOT_MOVED => TRIP END  => TRIP START" );
					pActivityReport.setTripFinished(true);
					dao.addOrUpdate(pActivityReport);
					dao.addOrUpdate(this.createNewActivity(device));
				}
				
				// VARIATIONS
				// TRIP START
				else if (tripHasStarted && p_TripHasStarted){
					logger.logInfo("Condition HAS_NOT_MOVED => TRIP START  => TRIP START" );
					//skip
				}
				else if (tripHasStarted && p_DeviceIsMoving){
					logger.logInfo("Condition HAS_NOT_MOVED => MOVING  => TRIP START" );
					pActivityReport.setTripFinished(true);
					dao.addOrUpdate(pActivityReport);
					dao.addOrUpdate(this.createNewActivity(device));
				}
				
				else if (tripHasStarted && p_DeviceIsStopped){
					logger.logInfo("Condition HAS_NOT_MOVED => STOPPED  => TRIP START" );
					pActivityReport.setTripFinished(true);
					dao.addOrUpdate(pActivityReport);
					dao.addOrUpdate(this.createNewActivity(device));
				}
				else if (tripHasStarted && p_TripHasEndedStarted){
					logger.logInfo("Condition HAS_NOT_MOVED => TRIP START  => TRIP START" );
				//skip
				}
				// TRIP ENDED
				else if (tripHasEnded && p_TripHasStarted){
					logger.logInfo("Condition HAS_NOT_MOVED => TRIP START  => TRIP END" );
					dao.delete(pActivityReport);
				}
				else if (tripHasEnded && p_TripHasEnded){
					logger.logInfo("Condition HAS_NOT_MOVED => TRIP END  => TRIP END" );
					//skip
				}
				else if (tripHasEnded && p_TripHasEndedStarted){
					logger.logInfo("Condition HAS_NOT_MOVED => TRIP END START  => TRIP END" );
					//skip
				}
				// MOVING
				else if (deviceIsMoving && p_DeviceIsMoving){
					logger.logInfo("Condition HAS_NOT_MOVED => MOVING  => MOVING" );
					//skip
				}
				else if (deviceIsMoving && p_DeviceIsStopped){
					logger.logInfo("Condition HAS_NOT_MOVED => STOPPED  => MOVING" );
					//skip
					
				}
				else if (deviceIsMoving && p_TripHasEnded){
					logger.logInfo("Condition HAS_NOT_MOVED => TRIP END  => MOVING" );
					pActivityReport.setTripFinished(true);
					dao.addOrUpdate(pActivityReport);
					dao.addOrUpdate(this.createNewActivity(device));
				}
				else if (deviceIsMoving && p_TripHasEndedStarted){
					logger.logInfo("Condition HAS_NOT_MOVED => TRIP END START  => MOVING" );
				}
				// STOPPED
				else if (deviceIsStopped && p_DeviceIsStopped){
					logger.logInfo("Condition HAS_NOT_MOVED => TRIP STOP  => STOP" );
					//skip
				}
				else if (deviceIsStopped && p_TripHasEnded){
					logger.logInfo("Condition HAS_NOT_MOVED => TRIP END  => STOP" );
					//skip
				}
				else if (deviceIsStopped && p_TripHasStarted){
					logger.logInfo("Condition HAS_NOT_MOVED => TRIP START  => STOP" );
					//skip
				}
				else if (deviceIsStopped && p_TripHasEndedStarted){
					logger.logInfo("Condition HAS_NOT_MOVED => TRIP END START  => STOP" );
					//skip
				}
			}
			
		} catch (JSONMapperException e) {
			logger.logError("Exception while parsing json.",e);
			MqClientFactory.mqClient.sendMessageToQueue(eventAuditBuilder.addEventType(EventType.UPDATE_ACTIVITY_REPORT).addIdType(DEVICE_ADDRESS).addIdValue(null).addInput(message).addFailure(true).addOutput("JSONMapperException: "+ e.getMessage()).toString(), EVENT_AUDITOR_QUEUE);
		} catch (HibernateHelperException e) {
			logger.logError("Exception while inserting device history  data.",e);
			MqClientFactory.mqClient.sendMessageToQueue(eventAuditBuilder.addEventType(EventType.UPDATE_ACTIVITY_REPORT).addIdType(DEVICE_ADDRESS).addIdValue(null).addInput(message).addFailure(true).addOutput("HibernateHelperException: "+ e.getMessage()).toString(),EVENT_AUDITOR_QUEUE);
		} catch (ObjectNotFoundException e) {
			logger.logError(e.getMessage(), e);
			MqClientFactory.mqClient.sendMessageToQueue(eventAuditBuilder.addEventType(EventType.UPDATE_ACTIVITY_REPORT).addIdType(DEVICE_ADDRESS).addIdValue(null).addInput(message).addFailure(true).addOutput("ObjectNotFoundException: "+ e.getMessage()).toString(),EVENT_AUDITOR_QUEUE);
		} catch (InvalidMessageException e) {
			logger.logError(e.getMessage(), e);
			MqClientFactory.mqClient.sendMessageToQueue(eventAuditBuilder.addEventType(EventType.UPDATE_ACTIVITY_REPORT).addIdType(DEVICE_ADDRESS).addIdValue(null).addInput(message).addFailure(true).addOutput("InvalidMessageException: "+ e.getMessage()).toString(),EVENT_AUDITOR_QUEUE);
	
		}
	}
	
	private DeviceState getDeviceState (ActivityReport pActivityReport) throws JSONMapperException, InvalidMessageException {
		if (pActivityReport == null) return DeviceState.TRIP_STARTED;
		if (pActivityReport.getAttributes() == null) return DeviceState.TRIP_STARTED;
		return this.getLastState(pActivityReport.getAttributes());
	}
	private Map<String, String> loadMap (ActivityReport pActivityReport ) throws JSONMapperException{
		if (pActivityReport == null) return new HashMap<String,String>();
		if (pActivityReport.getAttributes() == null) new HashMap<String,String>(); 
		return (Map<String, String>) JSONMapper.toObject(pActivityReport.getAttributes(), Map.class);
	}
	
	private boolean hasDeviceMoved (ActivityReport pActivityReport){
		if (pActivityReport == null) return false;
		return  super.hasDeviceMoved(pActivityReport.getStartLatitude(),pActivityReport.getStartLongitude());
	}
	
	private void updateActivity (ActivityReport pActivityReport) throws JSONMapperException{
		Map<String,String> attMap = loadMap(pActivityReport);
		addToArray(attMap, DeviceMessageAttributeType.DISTANCE, distance);
		addToArray(attMap, DeviceMessageAttributeType.SPEED, speed);
		addToArray(attMap, DeviceMessageAttributeType.STATE, deviceState.name());
		addToArray(attMap, DeviceMessageAttributeType.LATITUDE, latitude);
		addToArray(attMap, DeviceMessageAttributeType.LONGITUDE, longitude);
		addToArray(attMap, DeviceMessageAttributeType.COURSE, course);
		pActivityReport.setAttributes(JSONMapper.toString(attMap));
		pActivityReport.setEndLatitude(latitude);
		pActivityReport.setEndLongitude(longitude);
		pActivityReport.setEndTime(deviceTime);
		pActivityReport.setTripFinished(false);
		pActivityReport.setTotalDistance(calculateTotalDistance(attMap.get(DeviceMessageAttributeType.DISTANCE)));
		pActivityReport.setTotalTime(calculateTotalTime(pActivityReport.getStartTime(), pActivityReport.getEndTime()));
		pActivityReport.setAverageSpeed(calculateAverageSpeed(attMap.get(DeviceMessageAttributeType.SPEED)));
		pActivityReport.setMaxSpeed(calculateMaxSpeed(attMap.get(DeviceMessageAttributeType.SPEED)));

	}
	
	
	private ActivityReport createNewActivity (Device device) throws JSONMapperException {
		ActivityReport pActivityReport = new ActivityReport();
		pActivityReport.setAccountId(device.getAccount().getId());
		pActivityReport.setDateCreated(deviceTime);
		pActivityReport.setDeviceId(device.getId());
		pActivityReport.setLastUpdated(deviceTime);
		pActivityReport.setStartLatitude(latitude);
		pActivityReport.setStartLongitude(longitude);
		pActivityReport.setSystemTime(systemTime);
		pActivityReport.setStartTime(deviceTime);
		pActivityReport.setEndLatitude(latitude);
		pActivityReport.setEndLongitude(longitude);
		pActivityReport.setEndTime(deviceTime);
		pActivityReport.setTripFinished(false);
		Map<String,String> attMap = new HashMap<String,String>();
		addToArray(attMap, DeviceMessageAttributeType.DISTANCE, distance);
		addToArray(attMap, DeviceMessageAttributeType.SPEED, speed);
		addToArray(attMap, DeviceMessageAttributeType.STATE, DeviceState.TRIP_STARTED.name());
		addToArray(attMap, DeviceMessageAttributeType.LATITUDE, latitude);
		addToArray(attMap, DeviceMessageAttributeType.LONGITUDE, longitude);
		addToArray(attMap, DeviceMessageAttributeType.COURSE, course);
		pActivityReport.setAttributes(JSONMapper.toString(attMap));
		pActivityReport.setTotalDistance(calculateTotalDistance(attMap.get(DeviceMessageAttributeType.DISTANCE)));
		pActivityReport.setTotalTime(calculateTotalTime(pActivityReport.getStartTime(), pActivityReport.getEndTime()));
		pActivityReport.setAverageSpeed(calculateAverageSpeed(attMap.get(DeviceMessageAttributeType.SPEED)));
		pActivityReport.setMaxSpeed(calculateMaxSpeed(attMap.get(DeviceMessageAttributeType.SPEED)));
		return pActivityReport;
	}
	private DeviceState getLastState(String att) throws JSONMapperException{
		Map<String,String> object = (Map<String, String>) JSONMapper.toObject(att, Map.class);
		List<String> arr = (List<String>) JSONMapper.toObject(object.get("vehicleState"), List.class);
		String state =  arr.get(arr.size() -1);
		return DeviceState.valueOf(state);
	}
	private void addToArray (Map<String,String> object, String key, String value) throws JSONMapperException{
		
		if (value == null || value.equals("0")){
			logger.logInfo("value is not defined for key. "+ key);
			return;
		}
		List<String> list;
		if (object.containsKey(key)){
			String listAsString = object.get(key);
			list = (List<String>) JSONMapper.toObject(listAsString, List.class);
			list.add(value);
		} else {
			list  = new ArrayList<String>();
			list.add(value);
		}
		object.put(key, JSONMapper.toString(list));
	}
	
	private String calculateTotalDistance (String values)  {
		List<String> list = null;
		try {
		if (values == null) return "0";
		list = (List<String>) JSONMapper.toObject(values, List.class);
		if (list.isEmpty()) return "0";
		float previousDistance = 0;
		float currentDistance = 0;
		float totalDistance = 0;
		for (String distance : list) {
			currentDistance = Float.valueOf(distance);
			if (currentDistance < previousDistance){
				previousDistance = currentDistance;
				continue;
			}
			totalDistance = totalDistance + (currentDistance - previousDistance);
			previousDistance = currentDistance;
		}
		float finalTotal = (totalDistance / 1000); // convert to kilometer.
		BigDecimal bd = new BigDecimal(finalTotal).setScale(3, RoundingMode.HALF_EVEN);
		return JSONMapper.toString(bd);
		} catch (JSONMapperException e) {
			return "0";
		}
	}
	
	
	private String calculateTotalTime (Long startTime, Long endTime) throws JSONMapperException{
		return JSONMapper.toString(endTime - startTime);
	}
	private String calculateAverageSpeed (String values) throws JSONMapperException{
		if (values == null ) return "0";
		List<String> list = Collections.EMPTY_LIST;
		if (values != null)
		 list = (List<String>) JSONMapper.toObject(values, List.class);
		int size = list.size();
		Double totalSpeed = new Double(0);
		for (String l : list)
		{
			Double currentSpeed = Double.valueOf(l);
			totalSpeed = totalSpeed + currentSpeed;
		}
		Double average = totalSpeed / size;
		BigDecimal bd = null;
			try{
				bd = new BigDecimal(average).setScale(2, RoundingMode.HALF_EVEN);
			} catch (NumberFormatException e){
				logger.logError(e.getMessage() +" values: "+values);
				return "0";
			}
		return JSONMapper.toString(bd.doubleValue());
	}
	private String calculateMaxSpeed (String values) throws JSONMapperException{
		if (values == null ) return "0";
		List<String> list = (List<String>) JSONMapper.toObject(values, List.class);
		Double maxSpeed = new Double(0);
		for (String l : list)
		{
			Double speed = Double.valueOf(l);
			maxSpeed = Math.max(maxSpeed, speed);
		}
		BigDecimal bd = new BigDecimal(maxSpeed).setScale(2, RoundingMode.HALF_EVEN);
		return JSONMapper.toString(bd);
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


	public AccountWSClient getAccountWSClient() {
		return accountWSClient;
	}


	public void setAccountWSClient(AccountWSClient accountWSClient) {
		this.accountWSClient = accountWSClient;
	}
	

	
	
	
}
