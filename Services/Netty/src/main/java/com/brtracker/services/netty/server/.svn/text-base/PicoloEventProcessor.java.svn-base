package com.brtracker.services.netty.server;

import static com.brtracker.shared.utils.SystemConfiguration.*;
import static com.brtracker.shared.utils.packet.vendors.TeltonikaConstants.*;

import java.net.SocketAddress;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;

import com.brtracker.services.netty.DeviceConnection;
import com.brtracker.services.netty.MessageContext;
import com.brtracker.services.netty.MessageParser;
import com.brtracker.shared.payload.midlink.DeviceEvent;
import com.brtracker.shared.payload.midlink.DeviceEventType;
import com.brtracker.shared.utils.DateUtil;
import com.brtracker.shared.utils.activemq.JmsSender;
import com.brtracker.shared.utils.json.Json;
import com.brtracker.shared.utils.logging.MyLogger;
import com.brtracker.shared.utils.packet.PacketReaderController;
import com.brtracker.shared.utils.packet.PacketWriterController;
import com.brtracker.shared.utils.packet.TrackingMessage;

public class PicoloEventProcessor implements MessageEventProcessor {

	private MyLogger logger = new MyLogger(PicoloEventProcessor.class);
	private Logger messageLogger = Logger.getLogger("device.message.logger");

	private DeviceConnectionLookup deviceConnectionLookup;
	private PacketReaderController packetReader;
	private PacketWriterController packetWriter;
	private JmsSender jmsSender;
	private Map<Integer, DeviceProtocolHandler> deviceProtocolHandlers;
	
	public void processMessage(MessageEvent messageEvent, int port) {

		try {

			logger.logInfo("Processing message event start");
			
			ChannelBuffer buf = (ChannelBuffer) messageEvent.getMessage();
			
			MessageContext messageContext = MessageParser.readMessage(buf);
			messageContext.setPort(port);
			String messageReceived  = messageContext.getStrMessage();
			logger.logInfo("Message Received : " + messageReceived);
			loadUnitAddress(messageContext, messageEvent, port);
			
			DeviceConnection.put(messageContext.getUnitAddress(), messageReceived);
			
			messageLogger.info("(" + port + "|" + messageContext.getUnitAddress() + "|" + messageContext.getStrMessage() + ")");
			
			short[] unitPacket = createUnitPacket(messageContext, port);
			
			logMessageInfo(messageContext, port);
			
			logger.logInfo("Sending message to queue");
			
			jmsSender.sendStreamMessage(unitPacket, NETTY);
			
			logger.logInfo("calling Device protocol handler ");
			
			DeviceProtocolHandler deviceProtocolHandler = deviceProtocolHandlers.get(port);
			
			if (deviceProtocolHandler == null) {
				throw new NettyMessageProcessingException("Undefined Device Protocol handler for port " + port);
			}
			
			deviceProtocolHandler.handleMessage(messageContext, messageEvent);
			logger.logInfo("Storing message handler for device communication ");
			DeviceConnection.put(messageContext.getUnitAddress(), messageEvent);
			logger.logInfo("Processing message event end");
			
			
		} catch (Exception e) {
			logger.logError("Unexpected exception while processing message ", e);
		}
			
	}

	private void logMessageInfo(MessageContext messageContext, int port) {
		
		try {
			
			TrackingMessage tm = packetReader.readPacket(messageContext.getMessage(), port);
			
			String jsonString = tm.serialize();
			Json json = Json.read(jsonString);
			
			if (json.has(AVL_MESSAGE) && json.at(AVL_MESSAGE).has(AVL_DATA_ITEMS)) {
				List<Json> dataItems = json.at(AVL_MESSAGE).at(AVL_DATA_ITEMS).asJsonList();
				
				for (Json dataItem: dataItems) {
					
					if (dataItem.has(AVL_IO_DATA)) {
						
						if (dataItem.at(AVL_IO_DATA).has(AVL_1BYTE_PROPS)) {
							messageLogger.info("1b " + dataItem.at(AVL_IO_DATA).at(AVL_1BYTE_PROPS).toString());					
						}
						if (dataItem.at(AVL_IO_DATA).has(AVL_2BYTE_PROPS)) {
							messageLogger.info("2b " + dataItem.at(AVL_IO_DATA).at(AVL_2BYTE_PROPS).toString());
						}			
						if (dataItem.at(AVL_IO_DATA).has(AVL_4BYTE_PROPS)) {
							messageLogger.info("4b " + dataItem.at(AVL_IO_DATA).at(AVL_4BYTE_PROPS).toString());
						}			
						if (dataItem.at(AVL_IO_DATA).has(AVL_8BYTE_PROPS)) {
							messageLogger.info("8b " + dataItem.at(AVL_IO_DATA).at(AVL_8BYTE_PROPS).toString());
						}			
					}
				}
			}
		} catch (Exception e) {
			messageLogger.error("Exception while logMessageInfo ", e);
		}
	}

	private short[] createUnitPacket(MessageContext messageContext, int port) {
		
		Map<String, String> headers = new HashMap<String, String>();
		// port header
		headers.put("h01", String.valueOf(port));
		// unit id
		headers.put("h02", messageContext.getUnitAddress());
		// date
		headers.put("h03", String.valueOf(DateUtil.getUTCDateAndTime().getTime()));	
		
		short[] header = packetWriter.buildHeader(headers);
		short[] message = messageContext.getMessage();
		
		short[] devicePacket = new short[header.length + message.length];
		int i = 0;
		for (short s: header) { 
			devicePacket[i++] = s;
		}
		for (short s : messageContext.getMessage()) { 
			devicePacket[i++] = s;
		}
		return devicePacket;
	}

	private void loadUnitAddress(MessageContext messageContext, MessageEvent messageEvent, int port) {
		
		String deviceAddress = safeGetDeviceAddressFromMessage(messageContext, port);
		
		logger.logInfo("Fetching unit address from packet " + deviceAddress);
		
		if (deviceAddress == null) {
			deviceAddress = deviceConnectionLookup.getUnitAddress(messageEvent.getRemoteAddress());
			logger.logInfo("Fetching unit address by remote socket address " + deviceAddress);
			if (deviceAddress == null) {
				logger.logError("Unit address not found either in message or lookup");
				throw new NettyMessageProcessingException("Unit address not found");
			}
		} 
		
		messageContext.setUnitAddress(deviceAddress);
		
		deviceConnectionLookup.put(deviceAddress, messageEvent);
	}
	
	private String safeGetDeviceAddressFromMessage(MessageContext messageContext, int port) {
		String deviceAddress = null;
		try {
			deviceAddress = packetReader.findUnitIdentifier(messageContext.getMessage(), port);
		} catch (Exception e) {
			logger.logInfo("Could not retrieve device address from message");
		}
		return deviceAddress;
	}
	
	public void setJmsSender(JmsSender jmsSender) {
		this.jmsSender = jmsSender;
	}

	public void setDeviceConnectionLookup(
			DeviceConnectionLookup deviceConnectionLookup) {
		this.deviceConnectionLookup = deviceConnectionLookup;
	}

	public void setPacketReader(PacketReaderController packetReader) {
		this.packetReader = packetReader;
	}

	public void setPacketWriter(PacketWriterController packetWriter) {
		this.packetWriter = packetWriter;
	}

	public void setDeviceProtocolHandlers(
			Map<Integer, DeviceProtocolHandler> deviceProtocolHandlers) {
		this.deviceProtocolHandlers = deviceProtocolHandlers;
	}

	@Override
	public void handleException(ChannelHandlerContext ctx, ExceptionEvent e) {

		try {
			SocketAddress remoteAddress = ctx.getChannel().getRemoteAddress();
			
			String unitAddress = deviceConnectionLookup.getUnitAddress(remoteAddress);
			
			DeviceEvent event = new DeviceEvent();
			event.setUnitAddress(unitAddress);
			event.setTimestamp(GregorianCalendar.getInstance().getTimeInMillis());
			event.setEventType(DeviceEventType.DISCONNECTION.ordinal());
			
			ObjectMapper mapper = new ObjectMapper();
			String message = mapper.writeValueAsString(event);
			
			logger.logInfo("Handling exception for Picolo ");
			jmsSender.sendTextMessage(message, NETTY_DEV_EVENTS);
			
		} catch (Exception e1) {
			logger.logError("Handling exception for Picolo ", e1);
		}
		
	}

}
