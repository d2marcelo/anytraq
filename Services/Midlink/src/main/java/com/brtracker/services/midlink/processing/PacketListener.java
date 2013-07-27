package com.brtracker.services.midlink.processing;

import java.util.Arrays;

import javax.jms.Message;
import javax.jms.MessageEOFException;
import javax.jms.MessageListener;
import javax.jms.StreamMessage;
import javax.jms.TextMessage;

import com.brtracker.shared.utils.json.Json;
import com.brtracker.shared.utils.logging.MyLogger;

public class PacketListener implements MessageListener{

	private MyLogger logger = new MyLogger(PacketListener.class);
	private PacketDispatcher dispatcher;
	private RestfulMessageDispatcher restfulMessageDispatcher;
	
	@Override
	public void onMessage(Message amqMessage) {
		
		try {
			if (amqMessage instanceof StreamMessage) {
	
				logger.logInfo("PacketListener on stream message");
				short[] packet = extractPacket(amqMessage);
				dispatcher.dispatchPacket(packet);
				
			} else if (amqMessage instanceof TextMessage) {
				
				logger.logInfo("PacketListener on text message");
				
				TextMessage mqMessage = (TextMessage) amqMessage;
				String messageText = mqMessage.getText();
				Json jsonMessage = Json.read(messageText);
				
				restfulMessageDispatcher.dispatchPacket(jsonMessage);
				
			}
		} catch (Exception e) {
			logger.logError("Unexpected exception", e);
		}
	}

	private short[] extractPacket(Message amqMessage) throws Exception {
		short[] resultPacket = new short[] {};
		StreamMessage byteMessage = (StreamMessage) amqMessage;
		
		short[] buffer = new short[1500];
		int i = 0;
		while (i<buffer.length) {
			try {
				buffer[i++] = byteMessage.readShort();
			} catch (MessageEOFException e) {
				break;
			}
		}
		resultPacket = Arrays.copyOf(buffer, i);
		return resultPacket;
	}

	public void setDispatcher(PacketDispatcher dispatcher) {
		this.dispatcher = dispatcher;
	}

	public void setRestfulMessageDispatcher(
			RestfulMessageDispatcher restfulMessageDispatcher) {
		this.restfulMessageDispatcher = restfulMessageDispatcher;
	}
}
