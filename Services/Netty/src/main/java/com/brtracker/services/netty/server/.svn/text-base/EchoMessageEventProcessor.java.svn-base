package com.brtracker.services.netty.server;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;

import com.brtracker.shared.utils.logging.MyLogger;

public class EchoMessageEventProcessor implements MessageEventProcessor {

	private MyLogger logger = new MyLogger(EchoMessageEventProcessor.class);
	
	@Override
	public void processMessage(MessageEvent messageEvent, int port) {
		
		try {
			Class<?> eventClass = messageEvent.getMessage().getClass();
			if (String.class.isAssignableFrom(eventClass)) {
				String strMessage = (String) messageEvent.getMessage();
				byte[] bytes = strMessage.getBytes();
				logger.logInfo("Message Received: length=" + 
						bytes.length + " content [" + toHexString(bytes) + "]");
			} else {
				logger.logInfo("Message Received of unk type : " + eventClass.getName());
			}
			
		} catch (Exception e) {
			logger.logError("Unexpected exception", e);
		}
	}

	@Override
	public void handleException(ChannelHandlerContext ctx, ExceptionEvent e) {
		logger.logError("Exception Received " + e.getCause().getMessage());
	}	
	
	public static String toHexString(byte[] packet) {
		if (packet == null) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		for (int i=0; i< packet.length; i++) {
			if (sb.length() > 0) {
				sb.append(", ");
			}
			sb.append(Integer.toHexString(packet[i]));
		}
		return sb.toString();
	}


}
