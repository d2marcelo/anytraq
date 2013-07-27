package com.brtracker.services.midlink.processing;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.codehaus.jackson.map.ObjectMapper;

import com.brtracker.shared.payload.midlink.DeviceEvent;
import com.brtracker.shared.payload.tracking.DeviceMessageAttribute;
import com.brtracker.shared.utils.json.JSONMapper;
import com.brtracker.shared.utils.logging.MyLogger;
import static com.brtracker.services.midlink.processing.application.TrackingPayloadFields.*;
import static com.brtracker.shared.utils.SystemConfiguration.ASYNC;
import com.brtracker.shared.payload.controller.lookup.DeviceState;

public class DeviceEventListener implements MessageListener {

	private MyLogger logger = new MyLogger(DeviceEventListener.class);
	private ObjectMapper mapper = new ObjectMapper();
	private DeviceStateDao deviceStateDao;
	private MqClient mqClient;
	
	@Override
	public void onMessage(Message amqMessage) {

		logger.logInfo("DeviceEventListener on message ...");
		try {

			if (amqMessage instanceof TextMessage) {
				
				String payload = ((TextMessage) amqMessage).getText();
				DeviceEvent deviceEvent = mapper.readValue(payload,DeviceEvent.class);
				
				String unitAddress = deviceEvent.getUnitAddress();
				DeviceStateEntity dbDeviceState = deviceStateDao.getDeviceState(unitAddress);
				String timeStamp = String.valueOf(deviceEvent.getTimestamp());
				
				if (dbDeviceState != null) {
					
					DeviceMessageAttribute asyncRequest = createAsyncRequest(unitAddress, timeStamp, dbDeviceState);
					logger.logInfo("Sending device disabled message " + JSONMapper.toString(asyncRequest));
					mqClient.sendMessageToQueue(asyncRequest, ASYNC);
					
				} else {
					logger.logError("DeviceEventListener device not in local device state");
				}
				
			} else {
				logger.logError("DeviceEventListener received a non text message");
			}

		} catch (Exception e) {

		}
	}
	
	private DeviceMessageAttribute createAsyncRequest(String unitAddress, String timeStamp, DeviceStateEntity dbDeviceState) {
		
		DeviceMessageAttribute serviceRequest = new DeviceMessageAttribute(); 
		serviceRequest.setUnitAddress(unitAddress);
		serviceRequest.setMessageDate(timeStamp);
		serviceRequest.put(TRK_LONGITUDE, String.valueOf(dbDeviceState.getLng()));
		serviceRequest.put(TRK_LATITUDE, String.valueOf(dbDeviceState.getLat()));
		serviceRequest.put(TRK_STATE, DeviceState.OFF_LINE.name());
		serviceRequest.put(TRK_ACTION_NAME, "DeviceEventListener");
		serviceRequest.put(TRK_SOURCE_NAME, "DeviceEventListener");
		serviceRequest.put(TRK_COURSE, "");
		serviceRequest.put(TRK_TIME_MILLIS, timeStamp);
		serviceRequest.put(TRK_MSG_ID, "");
		
		return serviceRequest;
	}

	public void setDeviceStateDao(DeviceStateDao deviceStateDao) {
		this.deviceStateDao = deviceStateDao;
	}

	public void setMqClient(MqClient mqClient) {
		this.mqClient = mqClient;
	}

}
