package com.brtracker.services.midlink.processing.application;

import static com.brtracker.services.midlink.processing.application.TrackingPayloadFields.TRK_COURSE;
import static com.brtracker.services.midlink.processing.application.TrackingPayloadFields.TRK_LATITUDE;
import static com.brtracker.services.midlink.processing.application.TrackingPayloadFields.TRK_LONGITUDE;
import static com.brtracker.services.midlink.processing.application.TrackingPayloadFields.TRK_MSG_ID;
import static com.brtracker.services.midlink.processing.application.TrackingPayloadFields.TRK_SPEED;
import static com.brtracker.services.midlink.processing.application.TrackingPayloadFields.TRK_TIME_MILLIS;
import static com.brtracker.shared.utils.SystemConfiguration.ASYNC;
import static com.brtracker.shared.utils.SystemConfiguration.TRACKING;
import static com.brtracker.shared.utils.packet.vendors.SharedConstants.LAT;
import static com.brtracker.shared.utils.packet.vendors.SharedConstants.LNG;
import static com.brtracker.shared.utils.packet.vendors.SharedConstants.SPEED;
import static com.brtracker.shared.utils.packet.vendors.SharedConstants.TIMESTAMP;
import static com.brtracker.shared.utils.packet.vendors.SharedConstants.*;
import static com.brtracker.shared.utils.packet.vendors.TeltonikaConstants.AVL_DATA_ITEMS;
import static com.brtracker.shared.utils.packet.vendors.TeltonikaConstants.AVL_GPS_ANGLE;
import static com.brtracker.shared.utils.packet.vendors.TeltonikaConstants.AVL_GPS_DATA;
import static com.brtracker.shared.utils.packet.vendors.TeltonikaConstants.AVL_MESSAGE;

import java.util.List;

import com.brtracker.services.midlink.processing.DeviceStateAwareMessageAction;
import com.brtracker.services.midlink.processing.DeviceStateEntity;
import com.brtracker.services.midlink.processing.MessageAction;
import com.brtracker.services.midlink.processing.MqClient;
import com.brtracker.shared.payload.tracking.DeviceMessageAttribute;
import com.brtracker.shared.utils.SystemConfiguration;
import com.brtracker.shared.utils.gps.GpsUtils;
import com.brtracker.shared.utils.json.Json;
import com.brtracker.shared.utils.logging.MyLogger;
import com.brtracker.shared.utils.packet.TrackingMessage;

public class TeltonikaUnitStatusAction implements MessageAction, DeviceStateAwareMessageAction {

	protected MyLogger logger = new MyLogger(GpsUnitStatusAction.class);
	private ActionHelper actionHelper;
	private MqClient mqClient;
	private SystemConfiguration systemConfiguration;
	
	@Override
	public ActionName getName() {
		return ActionName.TeltonikaUnitStatusAction;
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
			logger.logInfo("TrackingMessage: "+trackingMessage.serialize());
			Json jsonMessage = Json.read(trackingMessage.serialize());
	
			String unitId = jsonMessage.at(AVL_MESSAGE).at(UNIT_ID).asString();
			String messageDate = jsonMessage.at(AVL_MESSAGE).at(MESSAGE_DATE).asString();
			DeviceMessageAttribute serviceRequest = new DeviceMessageAttribute();
			serviceRequest.setUnitAddress(unitId);
			serviceRequest.setMessageDate(messageDate);
			
			Json latestGpsMessage = getLatestGpsMessage(jsonMessage);
			
			if (latestGpsMessage != null) {
			
				String mLng = latestGpsMessage.at(AVL_GPS_DATA).at(LNG).asString();
				String mLat = latestGpsMessage.at(AVL_GPS_DATA).at(LAT).asString();
				
				serviceRequest.put(TRK_LATITUDE, mLat);
				serviceRequest.put(TRK_LONGITUDE, mLng);
				
				float angle = latestGpsMessage.at(AVL_GPS_DATA).at(AVL_GPS_ANGLE).asFloat();
				serviceRequest.put(TRK_COURSE, GpsUtils.angleToCourse(angle));
				
				String speed = latestGpsMessage.at(AVL_GPS_DATA).at(SPEED).asString();
				serviceRequest.put(TRK_SPEED, speed);
				
				String timestamp = latestGpsMessage.at(TIMESTAMP).asString();
				serviceRequest.put(TRK_TIME_MILLIS, timestamp);
				
				// not real message uniqueid, just piggybacking on the ts 
				serviceRequest.put(TRK_MSG_ID, timestamp);
				
				if (deviceState == null) {
					
					DeviceStateEntity currentDeviceState = new DeviceStateEntity();
					currentDeviceState.setUnitAddress(unitId);
					currentDeviceState.setLat(Double.valueOf(mLat));
					currentDeviceState.setLng(Double.valueOf(mLng));
					currentDeviceState.setSpeed(Double.valueOf(speed));
					currentDeviceState.setJsonDetails(trackingMessage.serialize());

					actionHelper.setRequestVehicleState(currentDeviceState, serviceRequest);
					
				} else {
					actionHelper.setDeviceStopped(serviceRequest, deviceState);
				}

				sendMessage(serviceRequest);

				logger.logInfo("Succesffully action TeltonikaUnitStatusAction executed on unit " + unitId);
				
			} else {
				logger.logInfo("TeltonikaUnitStatusAction got an empty GPS message" + unitId);
			}

		} catch (Exception e) {
			logger.logError("Unexpected exception ", e);
		}

	}

	private Json getLatestGpsMessage(Json jsonMessage) {
		List<Json> dataItems = jsonMessage.at(AVL_MESSAGE).at(AVL_DATA_ITEMS).asJsonList();
		long lastTimestamp = Long.MIN_VALUE;
		Json lastGpsMessage = null;
		
		for (Json j : dataItems) {
			long currentTimestamp = j.at(TIMESTAMP).asLong();
			if (currentTimestamp > lastTimestamp) {
				lastTimestamp = currentTimestamp;
				lastGpsMessage = j;
			}
		}
		return lastGpsMessage;
	}

	
	protected String getTrackingEndpoint() {
		return systemConfiguration.getEndpointURL(TRACKING) + "/";
	}

	protected void sendMessage(Object message) throws Exception {
		mqClient.sendMessageToQueue(message, ASYNC);
	}

	public void setSystemConfiguration(SystemConfiguration systemConfiguration) {
		this.systemConfiguration = systemConfiguration;
	}

	public void setMqClient(MqClient mqClient) {
		this.mqClient = mqClient;
	}

	public void setActionHelper(ActionHelper actionHelper) {
		this.actionHelper = actionHelper;
	}

}
