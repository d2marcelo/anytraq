package com.brtracker.services.midlink.processing.application;

import static com.brtracker.services.midlink.processing.application.TrackingPayloadFields.TRK_ACTION_NAME;
import static com.brtracker.services.midlink.processing.application.TrackingPayloadFields.TRK_SOURCE_NAME;
import static com.brtracker.shared.utils.packet.vendors.XirgoConstants.IGNITION_OFF_MSG;
import static com.brtracker.shared.utils.packet.vendors.XirgoConstants.IGNITION_ON_MSG;
import static com.brtracker.shared.utils.packet.vendors.XirgoConstants.PERIODIC_IGNITION_OFF_MSG;
import static com.brtracker.shared.utils.packet.vendors.XirgoConstants.PERIODIC_IGNITION_ON_MSG;
import static com.brtracker.shared.utils.packet.vendors.XirgoConstants.XIRGO_EVENT;
import static com.brtracker.shared.utils.packet.vendors.XirgoConstants.XIRGO_HEADING;
import static com.brtracker.shared.utils.packet.vendors.XirgoConstants.XIRGO_LAT;
import static com.brtracker.shared.utils.packet.vendors.XirgoConstants.XIRGO_LNG;
import static com.brtracker.shared.utils.packet.vendors.XirgoConstants.XIRGO_NUM_SAT;
import static com.brtracker.shared.utils.packet.vendors.XirgoConstants.XIRGO_SPEED;
import static com.brtracker.shared.utils.packet.vendors.XirgoConstants.XIRGO_UNIT_ADDRESS;

import java.util.Date;
import java.util.Map;

import com.brtracker.services.midlink.processing.BaseMessageAction;
import com.brtracker.services.midlink.processing.DeviceStateAwareMessageAction;
import com.brtracker.services.midlink.processing.DeviceStateDao;
import com.brtracker.services.midlink.processing.DeviceStateEntity;
import com.brtracker.shared.payload.controller.lookup.DeviceState;
import com.brtracker.shared.payload.tracking.DeviceMessageAttribute;
import com.brtracker.shared.utils.packet.TrackingMessage;

public class XirgoUnitStatusAction extends BaseMessageAction implements DeviceStateAwareMessageAction {

	private DeviceStateDao deviceStateDao;
	
	private int messageSeq = 0; 
	
	public ActionName getName() {
		return ActionName.XirgoUnitStatusAction;
	}

	@Override
	public void execute(TrackingMessage trackingMessage) {
		processMessage(trackingMessage, null);
	}
	
	@Override
	public void execute(TrackingMessage trackingMessage, DeviceStateEntity deviceState) {
		processMessage(trackingMessage, deviceState);
	}

	public void processMessage(TrackingMessage trackingMessage, DeviceStateEntity deviceState) {
		
		try {
			
			logger.logInfo("XirgoUnitStatusAction started ");
			
			String unitId = trackingMessage.getProperty(
					String.class, XIRGO_UNIT_ADDRESS).trim();

			Integer messageType = trackingMessage.getProperty(Integer.class, XIRGO_EVENT);
			
			switch (messageType) {
				case PERIODIC_IGNITION_ON_MSG:
					handlePeriodicIgnitionOn(messageType, trackingMessage, deviceState);
					break;
				case PERIODIC_IGNITION_OFF_MSG:
					handlePeriodicIgnitionOff(messageType, trackingMessage, deviceState);
					break;
				case IGNITION_ON_MSG:
					handleIgnitionOn(messageType, trackingMessage, deviceState);
					break;
				case IGNITION_OFF_MSG:
					handleIgnitionOff(messageType, trackingMessage, deviceState);
					break;
			}
			
			logger.logInfo("XirgoUnitStatusAction Succesffully executed on unit " + unitId);
			
		} catch (Exception e) {
			logger.logError("Unexpected exception processing " + getName(), e);
		} 
		
	}

	private void handlePeriodicIgnitionOff(Integer messageType, TrackingMessage trackingMessage, DeviceStateEntity deviceState) {
		try {

			String unitId = trackingMessage.getProperty(String.class, XIRGO_UNIT_ADDRESS).trim();

			logger.logInfo("handlePeriodicIgnitionOff started ");
			
			DeviceMessageAttribute serviceRequest = handleMessage(messageType, trackingMessage, deviceState, "perIgOff");
			
			if (serviceRequest != null) {
				actionHelper.setDeviceStopped(serviceRequest, deviceState);
				sendMessage(serviceRequest);
			}
			
			logger.logInfo("XirgoUnitStatusAction Succesffully executed on unit " + unitId);
			
		} catch (Exception e) {
			logger.logError("Unexpected exception processing " + getName(), e);
		} 
	}
	
	private void handlePeriodicIgnitionOn(Integer messageType, TrackingMessage trackingMessage, DeviceStateEntity deviceState) {
		try {

			String unitId = trackingMessage.getProperty(String.class, XIRGO_UNIT_ADDRESS).trim();

			logger.logInfo("handlePeriodicIgnitionOn started ");
			
			DeviceMessageAttribute serviceRequest = handleMessage(messageType, trackingMessage, deviceState, "perIgOn");
			
			if (serviceRequest != null) {
				sendMessage(serviceRequest);
			}
			
			logger.logInfo("XirgoUnitStatusAction Succesffully executed on unit " + unitId);
			
		} catch (Exception e) {
			logger.logError("Unexpected exception processing " + getName(), e);
		} 
	}
	
	private void handleIgnitionOn(Integer messageType, TrackingMessage trackingMessage, DeviceStateEntity deviceState) {
		try {

			String unitId = trackingMessage.getProperty(String.class, XIRGO_UNIT_ADDRESS).trim();

			logger.logInfo("handleIgnitionOn started ");
			
			DeviceMessageAttribute serviceRequest = handleMessage(messageType, trackingMessage, deviceState, "IgOn");
			
			if (serviceRequest != null) {
				actionHelper.setDeviceTripStarted(serviceRequest, deviceState);
				sendMessage(serviceRequest);
			}
			
			logger.logInfo("XirgoUnitStatusAction Succesffully executed on unit " + unitId);
			
		} catch (Exception e) {
			logger.logError("Unexpected exception processing " + getName(), e);
		} 
	}
	
	private void handleIgnitionOff(Integer messageType, TrackingMessage trackingMessage, DeviceStateEntity deviceState) {
		try {

			String unitId = trackingMessage.getProperty(String.class, XIRGO_UNIT_ADDRESS).trim();

			logger.logInfo("handleIgnitionOff started " + unitId);
			
			DeviceMessageAttribute serviceRequest = handleMessage(messageType, trackingMessage, deviceState, "IgOff");
			
			if (serviceRequest != null) {
				
				Double speed = trackingMessage.getProperty(Double.class, XIRGO_SPEED);
				
				if (speed == 0.0) {
					logger.logInfo("handleIgnitionOff end of trip " + unitId);
					actionHelper.setDeviceTripEnded(serviceRequest, deviceState);
				} else {
					logger.logInfo("handleIgnitionOff end of trip with non zero speed " + speed + " Unit " +  unitId);
				}
				sendMessage(serviceRequest);
			}
			
			logger.logInfo("XirgoUnitStatusAction Succesffully executed on unit " + unitId);
			
		} catch (Exception e) {
			logger.logError("Unexpected exception processing " + getName(), e);
		} 
	}

	private DeviceMessageAttribute handleMessage(Integer messageType, 
			TrackingMessage trackingMessage, DeviceStateEntity deviceState, String action) {
		
		try {
			
			String unitId = trackingMessage.getProperty(
					String.class, XIRGO_UNIT_ADDRESS).trim();

			String messageDate = String.valueOf(System.currentTimeMillis()); 
				//trackingMessage.getProperty(String.class, MESSAGE_DATE);
			
			logger.logInfo("handleMessage started ");
			
			/*
			 * These properties are not being extracted from payload 
			 * 
			 * "appMessage.gpsInfo.unitTime": "timeMillis",
               "appMessage.sequenceNumber": "messageUniqueId",
			 */
			
			Map<String, Object> messageProperties = getMessagePropertiesOrDie(trackingMessage, getName());
			
			Float headingAngle = trackingMessage.getProperty(Float.class, XIRGO_HEADING);
			
			logger.logInfo("Adding course to Xirgo message " + headingAngle);
			
			DeviceMessageAttribute serviceRequest = createDeviceMessageAttribute(
					unitId, messageDate, trackingMessage, messageProperties);

			// This should come in the packet
			Long unitTime = System.currentTimeMillis();
			
			serviceRequest.put("timeMillis", String.valueOf(unitTime));
			
			serviceRequest.put("messageCode", String.valueOf(messageType));
			
			serviceRequest.put("messageUniqueId", 
					String.valueOf(System.currentTimeMillis() + (++messageSeq)));
			
			serviceRequest.put("course", String.valueOf(headingAngle));
			
			logger.logInfo("XirgoUnitStatusAction has service request " + unitId);

			serviceRequest.put(TRK_ACTION_NAME, getName().getName());
			
			Double mLat = trackingMessage.getProperty(Double.class, XIRGO_LAT);
			Double mLng = trackingMessage.getProperty(Double.class, XIRGO_LNG);
			Double speed = trackingMessage.getProperty(Double.class, XIRGO_SPEED);
			
			Integer satellites = trackingMessage.getProperty(Integer.class, XIRGO_NUM_SAT);
			
			if (deviceState == null) {
				
				logger.logInfo("XirgoUnitStatusAction no db state available [" + unitId + "]");
				
				serviceRequest.put(TRK_SOURCE_NAME, "midlink_action_" + action);
				
				DeviceStateEntity currentDeviceState = new DeviceStateEntity();
				DeviceStateEntity dbDeviceState = deviceStateDao.getDeviceState(unitId);
				if (dbDeviceState != null) {
					currentDeviceState = dbDeviceState;
				}
				currentDeviceState.setUnitAddress(unitId);
				currentDeviceState.setLat(mLat);
				currentDeviceState.setLng(mLng);
				currentDeviceState.setSpeed(speed);
				currentDeviceState.setJsonDetails(trackingMessage.serialize());
				currentDeviceState.setLastActivityTs(new Date(unitTime));
				currentDeviceState.setSatellites(satellites);
				
				// preventing null state
				if (currentDeviceState.getState() == null) {
					currentDeviceState.setState(DeviceState.MOVING.name());
				}
				actionHelper.setRequestVehicleStatev2(currentDeviceState, serviceRequest);
			} else {
				
				logger.logInfo("XirgoUnitStatusAction db state available " + unitId);
				
				serviceRequest.put(TRK_SOURCE_NAME, "midlink_scheduler");
				
				actionHelper.setDeviceStoppedAndUpdate(serviceRequest, deviceState);
			}
			
			logger.logInfo("XirgoUnitStatusAction Succesffully executed on unit " + unitId);

			return serviceRequest;
			
		} catch (Exception e) {
			logger.logError("Unexpected exception processing " + getName(), e);
		} 
		return null;
	}
	
	public void setDeviceStateDao(DeviceStateDao deviceStateDao) {
		this.deviceStateDao = deviceStateDao;
	}

}
