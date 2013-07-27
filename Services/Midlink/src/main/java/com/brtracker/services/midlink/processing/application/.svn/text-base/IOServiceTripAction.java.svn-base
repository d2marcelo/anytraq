package com.brtracker.services.midlink.processing.application;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;

import com.brtracker.services.midlink.processing.BaseMessageAction;
import com.brtracker.services.midlink.processing.DeviceStateEntity;
import com.brtracker.shared.payload.tracking.DeviceMessageAttribute;
import com.brtracker.shared.utils.SystemConfiguration;
import com.brtracker.shared.utils.packet.PacketDefinitionConstants;
import com.brtracker.shared.utils.packet.TrackingMessage;
import static com.brtracker.services.midlink.processing.application.TrackingPayloadFields.*;
import static com.brtracker.shared.utils.SystemConfiguration.*;

public class IOServiceTripAction extends BaseMessageAction {

	private SystemConfiguration systemConfiguration;
	
	@Override
	public ActionName getName() {
		return ActionName.IoServiceTripAction;
	}

	@Override
	public void execute(TrackingMessage trackingMessage) {
		
		try {
			
			logger.logInfo("IoServiceTripAction: Processing IO report message ");
			String unitId = trackingMessage.getProperty(String.class, PacketDefinitionConstants.UNIT_ID);
			String messageDate= trackingMessage.getProperty(String.class, PacketDefinitionConstants.MESSAGE_DATE);
			
			Map<String, Object> messageProperties = getMessagePropertiesOrDie(trackingMessage, getName());
			
			DeviceMessageAttribute serviceRequest = createDeviceMessageAttribute(
					unitId, messageDate, trackingMessage, messageProperties);
			serviceRequest.put(TRK_ACTION_NAME, getName().getName());
			
			Double mLat = trackingMessage.getProperty(Double.class, 
					"appMessage.ioService.gpsData.latitude");
			
			Double mLng = trackingMessage.getProperty(Double.class, 
					"appMessage.ioService.gpsData.longitude");
			
			Double speed = trackingMessage.getProperty(Double.class, 
					"appMessage.ioService.gpsData.speed");
			
			Long unitTime = trackingMessage.getProperty(Long.class, 
					"appMessage.gpsInfo.unitTime");
			if (unitTime == null) {
				unitTime = GregorianCalendar.getInstance().getTimeInMillis();
			}
			if (mLat != null && mLng != null) {
				DeviceStateEntity currentDeviceState = new DeviceStateEntity();
				
				currentDeviceState.setUnitAddress(unitId);
				currentDeviceState.setLat(mLat);
				currentDeviceState.setLng(mLng);
				currentDeviceState.setSpeed(speed);
				currentDeviceState.setJsonDetails(trackingMessage.serialize());
				currentDeviceState.setLastActivityTs(new Date(unitTime));
				
				checkDryInput(trackingMessage, currentDeviceState, serviceRequest);
	
				sendMessage(serviceRequest);

				actionHelper.loadAndUpsertDeviceState(currentDeviceState);
				
				logger.logInfo("Succesffully action IOServiceAction executed on unit " + unitId);
			} else {
				logger.logInfo("Ignoring IO Service message: No location available on unit " + unitId);
			}

		} catch (Exception e) {
			logger.logError("Unexpected exception processing " + getName(), e);
		} 
		
	}

	/**
	 * This method might override the vehicle status (stopped/moving) according to the dry input 1
	 * which is assumed to be connected to the car's switch.
	 * In future versions, we might have the target dry input (1,2,3,4) set up via configuration
	 */
	private boolean checkDryInput(TrackingMessage tm, DeviceStateEntity deviceState,
			DeviceMessageAttribute serviceRequest) {
		
		boolean stateChanged = false;
		
		String switchDryInput = systemConfiguration.getConfigElement(
				MIDLINK, MIDLINK_SWITCH_DRYINPUT, "appMessage.ioService.dryInput5");
		
		Integer dryInput = tm.getProperty(Integer.class, switchDryInput);
		
		if (dryInput != null) {
			logger.logInfo("IoServiceTripAction: checking dry input " + switchDryInput);
			stateChanged = true;
			if (dryInput == 1) {
				logger.logInfo("IO Service reported DryInput as ON");
				actionHelper.setDeviceTripStarted(serviceRequest, deviceState);
			} else {
				logger.logInfo("IO Service reported DryInput as OFF");
				actionHelper.setDeviceTripEnded(serviceRequest, deviceState);
			}
		} else {
			logger.logInfo("IoServiceTripAction: no dry input available in message " + switchDryInput);
		}
		return stateChanged;
	}

	public void setSystemConfiguration(SystemConfiguration systemConfiguration) {
		this.systemConfiguration = systemConfiguration;
	}

}
