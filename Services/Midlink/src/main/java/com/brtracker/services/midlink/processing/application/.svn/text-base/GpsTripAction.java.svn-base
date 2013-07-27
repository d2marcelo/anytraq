package com.brtracker.services.midlink.processing.application;

import java.util.List;
import java.util.Map;

import com.brtracker.services.midlink.processing.BaseMessageAction;
import com.brtracker.services.midlink.processing.DeviceStateEntity;
import com.brtracker.shared.payload.tracking.DeviceMessageAttribute;
import com.brtracker.shared.utils.json.JSONPropertyAccessor;
import com.brtracker.shared.utils.packet.PacketDefinitionConstants;
import com.brtracker.shared.utils.packet.TrackingMessage;

public class GpsTripAction extends BaseMessageAction {

	@Override
	public ActionName getName() {
		return ActionName.GpsTripAction;
	}

	@Override
	public void execute(TrackingMessage trackingMessage) {
		
		try {
			String unitId = trackingMessage.getProperty(	
					String.class, PacketDefinitionConstants.UNIT_ID);
			String messageDate= trackingMessage.getProperty(	
					String.class, PacketDefinitionConstants.MESSAGE_DATE);
			Map<String, Object> messageProperties = getMessagePropertiesOrDie(trackingMessage, getName());
			
			DeviceMessageAttribute serviceRequest = createDeviceMessageAttribute(unitId, messageDate, trackingMessage, messageProperties);
			
			appendReasons(trackingMessage, messageProperties, serviceRequest);
			
			ActionUtils.addpendArrayOfIntegerProperties(trackingMessage, messageProperties, 
					serviceRequest, "appMessage.gpsInfo.alarmCounters");

			ActionUtils.addpendArrayOfIntegerProperties(trackingMessage, messageProperties, 
					serviceRequest, "appMessage.gpsInfo.reason");

			appendDeviceState(unitId, trackingMessage, serviceRequest);
			
			sendMessage(serviceRequest);
			
			logger.logInfo("Succesffully action GpsTripAction executed on unit " + unitId);
			
		} catch (Exception e) {
			logger.logError("Unexpected exception processing " + getName(), e);
		} 
	}
	
	private void appendDeviceState(String unitId, 
			TrackingMessage trackingMessage, DeviceMessageAttribute serviceRequest) throws Exception {
		
		Double mLat = trackingMessage.getProperty(Double.class, "appMessage.gpsInfo.gpsdata.latitude");
		Double mLng = trackingMessage.getProperty(Double.class, "appMessage.gpsInfo.gpsdata.longitude");
		Double speed = trackingMessage.getProperty(Double.class, "appMessage.gpsInfo.gpsdata.speed");
		Integer satellites = trackingMessage.getProperty(Integer.class, "appMessage.gpsInfo.satellite");
		
		DeviceStateEntity currentDeviceState = new DeviceStateEntity();
		currentDeviceState.setUnitAddress(unitId);
		currentDeviceState.setLat(mLat);
		currentDeviceState.setLng(mLng);
		currentDeviceState.setSpeed(speed);
		currentDeviceState.setJsonDetails(trackingMessage.serialize());
		currentDeviceState.setSatellites(satellites);
		
		actionHelper.setRequestVehicleState(currentDeviceState, serviceRequest);
	}
	
	@SuppressWarnings("unchecked")
	private void appendReasons(TrackingMessage trackingMessage, 
			Map<String, Object> messageProperties, DeviceMessageAttribute serviceRequest) {

		Integer atMode = trackingMessage.getProperty(Integer.class, "appMessage.gpsInfo.gpsdata.atmode");
		
		// Reason are meaningful iff the unit is not in AT Mode
		if (atMode == null) {
			JSONPropertyAccessor jsonAccessor = new JSONPropertyAccessor();
			List<Object> reasonTargetProps = (List<Object>) jsonAccessor.getDirectProperty(
					List.class, "appMessage.gpsInfo.reason", messageProperties);
			
			if (reasonTargetProps != null) {
				Integer[] reasonValues = trackingMessage.getProperty(Integer[].class, "appMessage.gpsInfo.reason");
				if (reasonValues != null) {
					
					for (int i = 0; i<reasonValues.length; i++) {
						Integer reasonIndex = reasonValues[i];
						if (reasonIndex < reasonTargetProps.size()) {
							String targetProperty = (String) reasonTargetProps.get(reasonIndex);
							if (targetProperty != null && targetProperty.length() > 0) {
								serviceRequest.put(targetProperty, reasonIndex.toString());
							}
						}
					}
				}
			}
		}
	}
	
}
