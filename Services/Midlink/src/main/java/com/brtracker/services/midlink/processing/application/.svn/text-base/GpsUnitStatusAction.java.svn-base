package com.brtracker.services.midlink.processing.application;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;

import com.brtracker.services.midlink.processing.BaseMessageAction;
import com.brtracker.services.midlink.processing.DeviceStateAwareMessageAction;
import com.brtracker.services.midlink.processing.DeviceStateDao;
import com.brtracker.services.midlink.processing.DeviceStateEntity;
import com.brtracker.shared.payload.tracking.DeviceMessageAttribute;
import com.brtracker.shared.utils.packet.PacketDefinitionConstants;
import com.brtracker.shared.utils.packet.TrackingMessage;
import static com.brtracker.services.midlink.processing.application.TrackingPayloadFields.*;

public class GpsUnitStatusAction extends BaseMessageAction implements DeviceStateAwareMessageAction {

	private DeviceStateDao deviceStateDao;
	
	public ActionName getName() {
		return ActionName.GpsUnitStatusAction;
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
			
			logger.logInfo("GpsUnitStatusAction started ");
			
			String unitId = trackingMessage.getProperty(String.class, PacketDefinitionConstants.UNIT_ID);

			String messageDate= trackingMessage.getProperty(String.class, PacketDefinitionConstants.MESSAGE_DATE);
			Map<String, Object> messageProperties = getMessagePropertiesOrDie(trackingMessage, getName());
			
			DeviceMessageAttribute serviceRequest = createDeviceMessageAttribute(unitId, messageDate, trackingMessage, messageProperties);

			logger.logInfo("GpsUnitStatusAction has service request " + unitId);

			serviceRequest.put(TRK_ACTION_NAME, getName().getName());
			
			Double mLat = trackingMessage.getProperty(Double.class, "appMessage.gpsInfo.gpsdata.latitude");
			Double mLng = trackingMessage.getProperty(Double.class, "appMessage.gpsInfo.gpsdata.longitude");
			Double speed = trackingMessage.getProperty(Double.class, "appMessage.gpsInfo.gpsdata.speed");
			Long unitTime = trackingMessage.getProperty(Long.class, "appMessage.gpsInfo.unitTime");
			Integer satellites = trackingMessage.getProperty(Integer.class, "appMessage.gpsInfo.satellite");
			
			if (unitTime == null) {
				unitTime = GregorianCalendar.getInstance().getTimeInMillis();
			}
			
			if (deviceState == null) {
				
				logger.logInfo("GpsUnitStatusAction no db state available " + unitId);
				
				serviceRequest.put(TRK_SOURCE_NAME, "midlink_action");
				
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
				
				actionHelper.setRequestVehicleStatev2(currentDeviceState, serviceRequest);
			} else {
				
				logger.logInfo("GpsUnitStatusAction db state available " + unitId);
				
				serviceRequest.put(TRK_SOURCE_NAME, "midlink_scheduler");
				
				actionHelper.setDeviceStoppedAndUpdate(serviceRequest, deviceState);
			}
			
			sendMessage(serviceRequest);
			
			logger.logInfo("GpsUnitStatusAction Succesffully executed on unit " + unitId);
			
		} catch (Exception e) {
			logger.logError("Unexpected exception processing " + getName(), e);
		} 
		
	}

	public void setDeviceStateDao(DeviceStateDao deviceStateDao) {
		this.deviceStateDao = deviceStateDao;
	}

}
