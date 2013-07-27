package com.brtracker.services.midlink.processing.application;

import static com.brtracker.services.midlink.processing.application.TrackingPayloadFields.TRK_LATITUDE;
import static com.brtracker.services.midlink.processing.application.TrackingPayloadFields.TRK_LONGITUDE;
import static com.brtracker.services.midlink.processing.application.TrackingPayloadFields.TRK_MSG_ID;
import static com.brtracker.services.midlink.processing.application.TrackingPayloadFields.TRK_SPEED;
import static com.brtracker.services.midlink.processing.application.TrackingPayloadFields.TRK_TIME_MILLIS;
import static com.brtracker.shared.utils.SystemConfiguration.ASYNC;
import static com.brtracker.shared.utils.SystemConfiguration.TRACKING;
import static com.brtracker.shared.utils.packet.vendors.SharedConstants.FUEL_ECONOMY;
import static com.brtracker.shared.utils.packet.vendors.SharedConstants.FUEL_LEVEL;
import static com.brtracker.shared.utils.packet.vendors.SharedConstants.FUEL_LEVEL_PRIMARY;
import static com.brtracker.shared.utils.packet.vendors.SharedConstants.LAT;
import static com.brtracker.shared.utils.packet.vendors.SharedConstants.LNG;
import static com.brtracker.shared.utils.packet.vendors.SharedConstants.SPEED;
import static com.brtracker.shared.utils.packet.vendors.SharedConstants.TIMESTAMP;
import static com.brtracker.shared.utils.packet.vendors.SharedConstants.TOTAL_DISTANCE;
import static com.brtracker.shared.utils.packet.vendors.SharedConstants.TOTAL_FUEL;
import static com.brtracker.shared.utils.packet.vendors.SharedConstants.*;
import static com.brtracker.shared.utils.packet.vendors.TeltonikaConstants.AVL_DATA_ITEMS;
import static com.brtracker.shared.utils.packet.vendors.TeltonikaConstants.AVL_GPS_DATA;
import static com.brtracker.shared.utils.packet.vendors.TeltonikaConstants.AVL_IO_DATA;
import static com.brtracker.shared.utils.packet.vendors.TeltonikaConstants.AVL_MESSAGE;

import java.util.ArrayList;
import java.util.List;

import com.brtracker.services.midlink.processing.MessageAction;
import com.brtracker.services.midlink.processing.MqClient;
import com.brtracker.services.midlink.processing.WsClient;
import com.brtracker.shared.payload.tracking.DeviceMessageAttribute;
import com.brtracker.shared.utils.SystemConfiguration;
import com.brtracker.shared.utils.json.Json;
import com.brtracker.shared.utils.logging.MyLogger;
import com.brtracker.shared.utils.packet.PacketDefinitionConstants;
import com.brtracker.shared.utils.packet.TrackingMessage;
import com.brtracker.shared.utils.wsutils.ServiceResponse;

public class TeltonikaTripAction implements MessageAction {

	protected MyLogger logger = new MyLogger(TeltonikaTripAction.class);
	private WsClient wsClient;
	private MqClient mqClient;
	private SystemConfiguration systemConfiguration;

	@Override
	public ActionName getName() {
		return ActionName.TeltonikaTripAction;
	}

	@Override
	public void execute(TrackingMessage trackingMessage) {

		try {
			Json jsonMessage = Json.read(trackingMessage.serialize());
			
			String unitId = jsonMessage.at(AVL_MESSAGE).at(UNIT_ID).asString();
			String messageDate= trackingMessage.getProperty(String.class, PacketDefinitionConstants.MESSAGE_DATE);
			List<DeviceMessageAttribute> deviceMessages = createDeviceMessageAttribute(unitId, messageDate, jsonMessage);
			
			if (deviceMessages.size() > 0) {
				
				//TODO use a bulk api to upload all the entries when available
				DeviceMessageAttribute serviceRequest = deviceMessages.get(0);
	
				sendMessage(serviceRequest);
				
				logger.logInfo("Succesffully action TeltonikaTripAction executed on unit " + unitId);
			}
		} catch (Exception e) {
			logger.logError("Unexpected exception ", e);
		}
	}

	private List<DeviceMessageAttribute> createDeviceMessageAttribute(String unitId, String messageDate ,Json json) {
		
		List<DeviceMessageAttribute> deviceMessages = new ArrayList<DeviceMessageAttribute>();
		
		List<Json> dataItems = json.at(AVL_MESSAGE).at(AVL_DATA_ITEMS).asJsonList();
		for (Json dataItem: dataItems) {
			
			DeviceMessageAttribute serviceRequest = new DeviceMessageAttribute();
			serviceRequest.setUnitAddress(unitId);
			serviceRequest.setMessageDate(messageDate);
			String timestamp = dataItem.at(TIMESTAMP).asString();
			serviceRequest.put(TRK_TIME_MILLIS, timestamp);
			
			if (dataItem.has(AVL_GPS_DATA) && dataItem.at(AVL_GPS_DATA).has(SPEED)) {
				serviceRequest.put(TRK_SPEED, dataItem.at(AVL_GPS_DATA).at(SPEED).asString());
			}
			
			if (dataItem.has(AVL_GPS_DATA) && dataItem.at(AVL_GPS_DATA).has(LAT)) {
				serviceRequest.put(TRK_LATITUDE, dataItem.at(AVL_GPS_DATA).at(LAT).asString());
			}
			
			if (dataItem.has(AVL_GPS_DATA) && dataItem.at(AVL_GPS_DATA).has(LNG)) {
				serviceRequest.put(TRK_LONGITUDE, dataItem.at(AVL_GPS_DATA).at(LNG).asString());
			}
			
			if (dataItem.has(AVL_IO_DATA) && dataItem.at(AVL_IO_DATA).has(FUEL_LEVEL)) {
				serviceRequest.put(FUEL_LEVEL_PRIMARY, dataItem.at(AVL_IO_DATA).at(FUEL_LEVEL).asString());
			}
			
			if (dataItem.has(AVL_IO_DATA) && dataItem.at(AVL_IO_DATA).has(TOTAL_FUEL)) {
				serviceRequest.put(TOTAL_FUEL, dataItem.at(AVL_IO_DATA).at(TOTAL_FUEL).asString());
			}
			
			if (dataItem.has(AVL_IO_DATA) && dataItem.at(AVL_IO_DATA).has(FUEL_ECONOMY)) {
				serviceRequest.put(FUEL_ECONOMY, dataItem.at(AVL_IO_DATA).at(FUEL_ECONOMY).asString());
			}
			
			if (dataItem.has(AVL_IO_DATA) && dataItem.at(AVL_IO_DATA).has(TOTAL_DISTANCE)) {
				serviceRequest.put(TOTAL_DISTANCE, dataItem.at(AVL_IO_DATA).at(TOTAL_DISTANCE).asString());
			}
			
			// just a fake message id
			serviceRequest.put(TRK_MSG_ID, timestamp);
			
			deviceMessages.add(serviceRequest);
		}
		return deviceMessages;
	}
	
	protected String getTrackingEndpoint() {
		return systemConfiguration.getEndpointURL(TRACKING) + "/";
	}

	protected ServiceResponse postRequest(Object message, String endpoint) throws Exception {
		return wsClient.postRequest(message, endpoint);
	}

	protected void sendMessage(Object message) throws Exception {
		mqClient.sendMessageToQueue(message, ASYNC);
	}

	public void setWsClient(WsClient wsClient) {
		this.wsClient = wsClient;
	}
	
	public void setSystemConfiguration(SystemConfiguration systemConfiguration) {
		this.systemConfiguration = systemConfiguration;
	}

	public void setMqClient(MqClient mqClient) {
		this.mqClient = mqClient;
	}
}
