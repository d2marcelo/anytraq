package com.brtracker.services.midlink.processing.application;

import static com.brtracker.shared.utils.SystemConfiguration.MSG_AUDIT;

import java.util.HashMap;
import java.util.Map;

import com.brtracker.shared.payload.midlink.MobileDeviceMessage;
import com.brtracker.shared.utils.activemq.JmsSender;
import com.brtracker.shared.utils.json.JSONMapper;
import com.brtracker.shared.utils.json.Json;
import com.brtracker.shared.utils.logging.MyLogger;
import com.brtracker.shared.utils.packet.PacketDefinitionConstants;
import com.brtracker.shared.utils.packet.PacketPrinterUtils;
import com.brtracker.shared.utils.packet.TrackingMessage;

public class MessageAuditLogger {

	protected MyLogger logger = new MyLogger(MessageAuditLogger.class);
	private JmsSender jmsSender;
	
	public void auditMessage(Json jsonMessage) {
		try {
			String payload = jsonMessage.toString();
			MobileDeviceMessage message = (MobileDeviceMessage) JSONMapper.toObject(payload, MobileDeviceMessage.class);
			
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("messageType", message.getDeviceType().name());
			data.put("parsedMessage", payload);
			data.put("rawMessage", payload);
			data.put("unitAddress", message.getDeviceAddress());
			String parsedData = JSONMapper.toString(data);
			jmsSender.sendTextMessage(parsedData, MSG_AUDIT);
			
			logger.logInfo("Message successfuly audited");
			
		} catch (Exception e) {
			logger.logError("Error auditing the given packet", e);
		}
	}
	
	public void auditMessage(TrackingMessage tm, short[] packet) {
		
		try {
			
			String unitId = tm.getProperty(String.class, PacketDefinitionConstants.UNIT_ID);
			
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("messageType", tm.getMessageType());
			data.put("parsedMessage", tm.serialize());
			
			String rawMessage = "\"" + PacketPrinterUtils.toHexString(packet) + "\"";
			data.put("rawMessage", rawMessage);
			data.put("unitAddress", unitId);

			String message = JSONMapper.toString(data);
			jmsSender.sendTextMessage(message, MSG_AUDIT);
			
			logger.logInfo("Message successfuly audited " + message);
			
		} catch (Exception e) {
			logger.logError("Error auditing the given packet", e);
		}
	}

	public void setJmsSender(JmsSender jmsSender) {
		this.jmsSender = jmsSender;
	}
}
