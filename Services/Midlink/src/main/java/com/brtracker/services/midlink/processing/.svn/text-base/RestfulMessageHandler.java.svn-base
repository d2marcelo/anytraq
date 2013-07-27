package com.brtracker.services.midlink.processing;

import static com.brtracker.services.midlink.processing.application.TrackingPayloadFields.*;
import static com.brtracker.shared.utils.SystemConfiguration.ASYNC;

import java.util.Date;

import com.brtracker.services.midlink.processing.application.ActionHelper;
import com.brtracker.shared.payload.midlink.MobileDeviceEventType;
import com.brtracker.shared.payload.midlink.MobileDeviceMessage;
import com.brtracker.shared.payload.tracking.DeviceMessageAttribute;
import com.brtracker.shared.utils.gps.GpsUtils;
import com.brtracker.shared.utils.json.JSONMapper;
import com.brtracker.shared.utils.json.Json;
import com.brtracker.shared.utils.logging.MyLogger;

public class RestfulMessageHandler {

	private DeviceStateDao deviceStateDao;
	
	private MyLogger logger = new MyLogger(MessageTypeHandler.class);
	private ActionHelper actionHelper;
	private MqClient mqClient;
	
	public void handleMessage(Json jsonMessage) {
		
		try {
			
			String payload = jsonMessage.toString();
			
			logger.logInfo("RestfulMessageHandler handling restful message " + payload);
			
			MobileDeviceMessage message = (MobileDeviceMessage) JSONMapper.toObject(
					payload, MobileDeviceMessage.class);
			
			String deviceAddress = message.getDeviceAddress();
			long timeStamp = message.getTimeStamp();
			
			DeviceMessageAttribute serviceRequest = createDeviceMessageAttribute(
					deviceAddress, timeStamp, message);

			logger.logInfo("RestfulMessageHandler initial service request built " + deviceAddress);
			
			serviceRequest.put(TRK_SOURCE_NAME, "midlink_action");
			
			DeviceStateEntity currentDeviceState = new DeviceStateEntity();
			DeviceStateEntity dbDeviceState = deviceStateDao.getDeviceState(deviceAddress);
			if (dbDeviceState != null) {
				currentDeviceState = dbDeviceState;
			}
			currentDeviceState.setUnitAddress(deviceAddress);
			currentDeviceState.setLat(message.getLatitude());
			currentDeviceState.setLng(message.getLongitude());
			currentDeviceState.setSpeed(message.getSpeed());
			currentDeviceState.setJsonDetails(payload);
			currentDeviceState.setLastActivityTs(new Date(timeStamp));
			
			Integer satellites = getSatellites(message);
			currentDeviceState.setSatellites(satellites);
			
			actionHelper.setRequestVehicleStatev2(currentDeviceState, serviceRequest);
			
			System.out.println("Sending message to Async " + JSONMapper.toString(message));
			mqClient.sendMessageToQueue(serviceRequest, ASYNC);

			logger.logInfo("RestfulMessageHandler sending service request " + JSONMapper.toString(serviceRequest));
			
		} catch (Exception e) {
			logger.logError("Unexpected exception " + jsonMessage.toString(), e);
		}
	}

	private Integer getSatellites(MobileDeviceMessage message) {
		MobileDeviceEventType eventType = message.getMobileDeviceEventType();
		return MobileDeviceEventType.GPS_UNAVAILABLE.equals(eventType) ? 0 : 1; 
	}

	private DeviceMessageAttribute createDeviceMessageAttribute(
			String deviceId, long timeStamp, MobileDeviceMessage message) {
		
		DeviceMessageAttribute dma = new DeviceMessageAttribute();
		dma.setUnitAddress(deviceId);
		dma.setMessageDate(String.valueOf(timeStamp));
		dma.put("messageType", message.getDeviceType().name());

		dma.put(TRK_LATITUDE, String.valueOf(message.getLatitude()));
		dma.put(TRK_LONGITUDE, String.valueOf(message.getLongitude()));
		dma.put(TRK_ACTION_NAME, "RestfulMessageHandler");
		String course = GpsUtils.angleToCourse(Double.valueOf(message.getHeading()).floatValue());
		dma.put(TRK_COURSE, course);
		dma.put(TRK_SPEED, String.valueOf(message.getSpeed()));
		if (!message.getMediaFileName().isEmpty()) {
		dma.put(TRK_MEDIA_FILE_NAME, message.getMediaFileName());
		dma.put(TRK_MEDIA_FILE_COMMENT, message.getMediaFileComment());
		}
		dma.put(TRK_TIME_MILLIS, String.valueOf(message.getTimeStamp()));
		// TODO add distance
		// TODO add message id 
		return dma;
	}

	public void setDeviceStateDao(DeviceStateDao deviceStateDao) {
		this.deviceStateDao = deviceStateDao;
	}

	public void setActionHelper(ActionHelper actionHelper) {
		this.actionHelper = actionHelper;
	}

	public void setMqClient(MqClient mqClient) {
		this.mqClient = mqClient;
	}
}
