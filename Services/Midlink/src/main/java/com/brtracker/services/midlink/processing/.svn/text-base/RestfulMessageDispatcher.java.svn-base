package com.brtracker.services.midlink.processing;

import static com.brtracker.shared.utils.SystemConfiguration.ASYNC;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.lang.StringUtils;

import com.brtracker.services.midlink.processing.application.MessageAuditLogger;
import com.brtracker.shared.payload.controller.lookup.MessageType;
import com.brtracker.shared.payload.tracking.DeviceMessageAttribute;
import com.brtracker.shared.utils.json.Json;
import com.brtracker.shared.utils.logging.MyLogger;

public class RestfulMessageDispatcher {

	private MyLogger logger = new MyLogger(RestfulMessageDispatcher.class);
	
	private Map<String, RestfulMessageHandler> deviceTypeHandlers;
	private ExecutorService executorService = Executors.newCachedThreadPool();
	private MessageAuditLogger messageAuditLogger;
	private MqClient mqClient;
	
	public void dispatchPacket(final Json jsonMessage) {
		
		String nagiosKey = jsonMessage.at("nagiosKey").asString();
		
		// Monitoring message
		if (!StringUtils.isEmpty(nagiosKey)) {
			
			forwardMonitoringMessage(jsonMessage);
			
		} else {
		
			String deviceType = jsonMessage.at("deviceType").asString();
			
			messageAuditLogger.auditMessage(jsonMessage);
			
			final RestfulMessageHandler deviceTypeHandler = deviceTypeHandlers.get(deviceType);
			
			if (deviceTypeHandler != null) {
				logger.logInfo("PacketDispatcher message type handler found for " + deviceType);
				executorService.execute(new Runnable() {
	
					@Override
					public void run() {
						deviceTypeHandler.handleMessage(jsonMessage);
					}
					
				});
				logger.logInfo("PacketDispatcher message dispatched for " + deviceType);
			} else {
				logger.logError("Message handle not found for message type " + deviceType);
			}
		}
	}

	private void forwardMonitoringMessage(Json jsonMessage) {
		
		try {
			
			String messageType = MessageType.MonitoringMessage.name();
			
			DeviceMessageAttribute serviceRequest = new DeviceMessageAttribute();
			serviceRequest.setUnitAddress("00000001");
			serviceRequest.setMessageDate(String.valueOf(0));
			serviceRequest.put("messageType", messageType);
			
			String nagiosKey = jsonMessage.at("nagiosKey").asString();
			
			logger.logInfo("Processing monitoring message " + messageType + " " + nagiosKey);

			serviceRequest.put("nagiosKey", nagiosKey);
			
			mqClient.sendMessageToQueue(serviceRequest, ASYNC);
			
		} catch (Exception e) {
			logger.logError("Error forwarding monitoring message", e);
		}
	}

	public void setDeviceTypeHandlers(
			Map<String, RestfulMessageHandler> deviceTypeHandlers) {
		this.deviceTypeHandlers = deviceTypeHandlers;
	}

	public void setMessageAuditLogger(MessageAuditLogger messageAuditLogger) {
		this.messageAuditLogger = messageAuditLogger;
	}

	public void setMqClient(MqClient mqClient) {
		this.mqClient = mqClient;
	}
	
}
