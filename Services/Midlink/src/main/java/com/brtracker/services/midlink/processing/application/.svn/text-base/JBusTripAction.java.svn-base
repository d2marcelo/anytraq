package com.brtracker.services.midlink.processing.application;

import java.util.Map;

import com.brtracker.services.midlink.processing.BaseMessageAction;
import com.brtracker.services.midlink.processing.DeviceStateEntity;
import com.brtracker.shared.payload.tracking.DeviceMessageAttribute;
import com.brtracker.shared.utils.packet.PacketDefinitionConstants;
import com.brtracker.shared.utils.packet.TrackingMessage;
import static com.brtracker.services.midlink.processing.application.TrackingPayloadFields.*;

public class JBusTripAction extends BaseMessageAction {

	public ActionName getName() {
		return ActionName.JBusTripAction;
	}

	public void execute(TrackingMessage trackingMessage) {

		try {
			String unitId = trackingMessage.getProperty(String.class, PacketDefinitionConstants.UNIT_ID);
			String messageDate= trackingMessage.getProperty(String.class, PacketDefinitionConstants.MESSAGE_DATE);
			Map<String, Object> messageProperties = getMessagePropertiesOrDie(trackingMessage, getName());

			DeviceMessageAttribute serviceRequest = createDeviceMessageAttribute(unitId, messageDate, trackingMessage, messageProperties);
			serviceRequest.put(TRK_ACTION_NAME, getName().getName());
			
			serviceRequest.getAttributes().put("timeMillis", String.valueOf(System.currentTimeMillis()));

			Integer tripState = trackingMessage.getProperty(Integer.class, "appMessage.jBus.tripState");

			appendDeviceState(unitId, trackingMessage, serviceRequest);
			
			if (tripState != null) {
				
				sendMessage(serviceRequest);
				
				logger.logInfo("Succesffully action TripAction executed on unit " + unitId);
				
			} else {
				logger.logError("action TripAction on unit " + unitId + " detected a missing tripState");
			}

		} catch (Exception e) {
			logger.logError("Unexpected exception processing " + getName(), e);
		} 
	}
	
	private void appendDeviceState(String unitId, 
			TrackingMessage trackingMessage, DeviceMessageAttribute serviceRequest) throws Exception {
		
		Double mLat = trackingMessage.getProperty(Double.class, "appMessage.jBus.gpsData.latitude");
		Double mLng = trackingMessage.getProperty(Double.class, "appMessage.jBus.gpsData.longitude");
		Double speed = trackingMessage.getProperty(Double.class, "appMessage.jBus.gpsData.speed");

		DeviceStateEntity currentDeviceState = new DeviceStateEntity();
		currentDeviceState.setUnitAddress(unitId);
		currentDeviceState.setLat(mLat);
		currentDeviceState.setLng(mLng);
		currentDeviceState.setSpeed(speed);
		currentDeviceState.setJsonDetails(trackingMessage.serialize());
		
		actionHelper.setMovingStateFromDb(currentDeviceState, serviceRequest);
	}

}
